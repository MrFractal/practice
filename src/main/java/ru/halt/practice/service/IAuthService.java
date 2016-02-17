package ru.halt.practice.service;

import ru.halt.practice.util.LoginUserInfo;

/**
 * Created by Petr Rudenko on 06.02.2016.
 */
public interface IAuthService {
    boolean loginUser(LoginUserInfo userInfo);
}
