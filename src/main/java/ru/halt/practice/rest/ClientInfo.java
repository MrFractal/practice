package ru.halt.practice.rest;

/**
 * Created by Petr Rudenko on 13.02.2016.
 */
public class ClientInfo {
    private Long id;
    private String login;
    private String password;

    public ClientInfo(){

    }

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
}
