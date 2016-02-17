package ru.halt.practice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * Created by Petr Rudenko on 10.02.2016.
 */
@Component
public class ClientAuthenticationProvider extends DaoAuthenticationProvider {
    @Autowired
    @Override
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        super.setUserDetailsService(userDetailsService);
    }

}
