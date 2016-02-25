package ru.halt.practice.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by Petr Rudenko on 18.02.2016.
 */
public class CurrentUser extends User {
    private Long id;

    public CurrentUser(Long id, String username, String password, boolean isEnabled, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, isEnabled, true, true, true, authorities);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "id=" + id +
                '}';
    }
}
