package com.yryun.system.service;

import com.yryun.system.model.user.User;
import com.yryun.system.utils.ApiR;
import com.yryun.system.utils.ApiResponse;

import java.util.Map;

public interface LoginService {

    ApiR login(User user);

    ApiR logout();

}
