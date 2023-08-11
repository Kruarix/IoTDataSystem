package com.yryun.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yryun.system.mapper.UserMapper;
import com.yryun.system.model.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserDetailsService,UserDetailsPasswordService {

    private final UserMapper userMapper;


    /**
     * 获取当前用户
     * @return 当前用户
     */
    public User currentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();  // 获取当前认证信息
        final String username = ((UserDetails) authentication.getPrincipal()).getUsername();  // 获取认证主体的用户名
        return userMapper.selectOne(new QueryWrapper<User>().eq("username", username));  // 通过用户名查询用户信息
    }

    /**
     * 根据用户名查询用户的认证授权信息
     * @param username 用户名
     * @return LoginUser
     * @throws UsernameNotFoundException 异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq(User.Fields.username, username));  // 通过用户名查询用户信息
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }

    /**
     * 修改密码
     * @param user        用户
     * @param newPassword 新密码
     * @return UserDetails
     */
    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        User userDb = userMapper.selectOne(new QueryWrapper<User>().eq(User.Fields.username, user.getUsername()));
        userDb.setPassword(newPassword);
        userMapper.updateById(userDb);
        return userDb;
    }

}
