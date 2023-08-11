package com.yryun.system.service.impl;

import com.yryun.system.model.user.User;
import com.yryun.system.service.LoginService;
import com.yryun.system.utils.*;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public ApiR login(User user) {

        //通过UsernamePasswordAuthenticationToken获取用户名和密码
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());

        //AuthenticationManager委托机制对authenticationToken 进行用户认证
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //如果认证没有通过，给出对应的提示
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败");
        }

        //如果认证通过，使用user生成jwt  jwt存入ResponseResult 返回

        //如果认证通过，拿到这个当前登录用户信息
        User loginUser = (User) authenticate.getPrincipal();

        //获取当前用户的userid
        String userid = loginUser.getId().toString();

        String jwt = JwtUtil.createJWT(userid);
        Map<String, String> map = new HashMap<>();
        map.put("token",jwt);

        //把完整的用户信息存入redis  userid为key   用户信息为value
        redisUtil.setCacheObject("login:"+userid,loginUser);

        return ApiR.success(map, "登录成功");
    }

    @Override
    public ApiR logout() {
        //从SecurityContextHolder中的userid
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();
        Long userid = user.getId();

        //根据userid找到redis对应值进行删除
        redisUtil.deleteObject("login:"+userid);
        return ApiR.success(null,"注销成功");

    }

}
