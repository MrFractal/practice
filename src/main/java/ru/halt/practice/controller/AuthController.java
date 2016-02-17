package ru.halt.practice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.halt.practice.domain.Client;
import ru.halt.practice.exception.UserNotFound;
import ru.halt.practice.repository.ClientRepository;
import ru.halt.practice.rest.LoginRequest;
import ru.halt.practice.rest.LoginResponse;
import ru.halt.practice.service.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * Created by Petr Rudenko on 09.02.2016.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);


    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    private AuthService authService;

    @Autowired
    private ClientRepository clientRepository;


    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
        LOGGER.info("principal = " + principal + " principal.getName() = " + principal.getName());
        System.out.println("principal = " + principal + " principal.getName() = " + principal.getName());
        return principal.getName();
    }


    @RequestMapping(value = "/byToken", method = RequestMethod.GET)
    @ResponseBody
    public String byToken(Authentication authentication) {
        LOGGER.info("authentication = " + authentication + " authentication.getName() = " + authentication.getName());
        return authentication.getName();
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletRequest request){

        Client client = clientRepository.findByLoginAndPassword(loginRequest.getLoginUserInfo().getLogin(), loginRequest.getLoginUserInfo().getPassword());

        String[] str = (String[])client.getClientRoles().toArray();

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(loginRequest.getLoginUserInfo().getLogin(), loginRequest.getLoginUserInfo().getPassword(), AuthorityUtils.createAuthorityList(str)));
//
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getLoginUserInfo().getLogin(), loginRequest.getLoginUserInfo().getPassword());
//        token.setDetails(new WebAuthenticationDetails(request));
//
//        Authentication auth = authenticationManagerBuilder.getObject().authenticate(token);
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        securityContext.setAuthentication(auth);
//
//        if(auth.isAuthenticated()){
//            HttpSession session = request.getSession(true);
//            session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
//
//
//        }else{
//            SecurityContextHolder.getContext().setAuthentication(null);
//
//            System.out.println("FAILED");
//
//            throw new UserNotFound("xxxxxxxxxxxxxx sssssssssss");
//        }


        String result = authService.loginUser(loginRequest.getLoginUserInfo()) ? "ok" : "bad";
        return new LoginResponse(result);
    }
}
