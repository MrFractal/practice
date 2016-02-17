package ru.halt.practice.rest;

import ru.halt.practice.util.LoginUserInfo;
import ru.halt.practice.util.UserType;

/**
 * Created by Petr Rudenko on 06.02.2016.
 */
public class LoginRequest {
    private LoginUserInfo loginUserInfo;

    public LoginUserInfo getLoginUserInfo() {
        return loginUserInfo;
    }

    public void setLoginUserInfo(LoginUserInfo loginUserInfo) {
        this.loginUserInfo = loginUserInfo;
    }
}
