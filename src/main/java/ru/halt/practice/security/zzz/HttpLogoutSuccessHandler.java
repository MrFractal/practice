package ru.halt.practice.security.zzz;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Petr Rudenko on 11.02.2016.
 */

@Component
public class HttpLogoutSuccessHandler implements LogoutSuccessHandler {
    //@Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        System.out.println("IN HTTP LOGOUT SUCCESS HANDLER");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().flush();
    }
}

