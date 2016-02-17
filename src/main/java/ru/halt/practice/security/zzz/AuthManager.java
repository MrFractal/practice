package ru.halt.practice.security.zzz;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * Created by Petr Rudenko on 12.02.2016.
 */
@Component
public class AuthManager implements AuthenticationManager {

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }
}
