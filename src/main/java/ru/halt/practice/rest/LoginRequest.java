package ru.halt.practice.rest;

import ru.halt.practice.util.LoginUserInfo;
import ru.halt.practice.util.UserType;

/**
 * Created by Petr Rudenko on 06.02.2016.
 */
public class LoginRequest {
    private Long id;
    private String login;
    private String password;
    private String token;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
