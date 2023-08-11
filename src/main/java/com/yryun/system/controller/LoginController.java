package com.yryun.system.controller;

import com.yryun.system.model.user.User;
import com.yryun.system.service.LoginService;
import com.yryun.system.utils.ApiR;
import com.yryun.system.utils.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public ApiR login(@RequestBody User user){
        return loginService.login(user);
    }

}
