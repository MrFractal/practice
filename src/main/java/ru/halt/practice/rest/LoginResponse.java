package ru.halt.practice.rest;

/**
 * Created by Petr Rudenko on 06.02.2016.
 */
public class LoginResponse {
    private String str = "ok";

    public LoginResponse(){

    }

    public LoginResponse(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
