package ru.halt.practice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.halt.practice.security.ClientDetailsService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**
 * Created by Petr Rudenko on 13.02.2016.
 */
@Service
public class StatelessLoginFilter implements Filter {
    @Autowired
    @Qualifier("clientDetailsService")
    private UserDetailsService userDetailsService;

    private String pass;

    StatelessLoginFilter(){

    }

    StatelessLoginFilter(String pass){
        this.pass = pass;
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("In StatelessLoginFilter init");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        Principal principal = request.getUserPrincipal();
        System.out.println("principal = " + principal);
    }


//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
//                                            FilterChain chain, Authentication authentication) throws IOException, ServletException {
//
//        // Lookup the complete User object from the database and create an Authentication for it
//        final ClientDetailsService authenticatedUser = (ClientDetailsService)userDetailsService.loadUserByUsername(authentication.getName());
//        final UserAuthentication userAuthentication = new UserAuthentication(authenticatedUser);
//
//        // Add the custom token as HTTP header to the response
//        tokenAuthenticationService.addAuthentication(response, userAuthentication);
//
//        // Add the authentication to the Security context
//        SecurityContextHolder.getContext().setAuthentication(userAuthentication);
//    }

    public void destroy() {

    }
}
