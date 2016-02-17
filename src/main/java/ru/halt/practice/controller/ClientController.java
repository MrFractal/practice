package ru.halt.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.halt.practice.domain.Client;
import ru.halt.practice.rest.ClientInfo;
import ru.halt.practice.rest.LoginRequest;
import ru.halt.practice.rest.LoginResponse;
import ru.halt.practice.rest.PageSearch;
import ru.halt.practice.service.IClientService;
import ru.halt.practice.util.ModelUtil;
import ru.halt.practice.util.RestResponse;

import java.security.Principal;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * Created by Petr Rudenko on 25.01.2016.
 */
@Controller
@RequestMapping("/client")
public class ClientController {
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("i18n.messages", Locale.getDefault());
    @Autowired
    private IClientService<Client> clientService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String printWelcome(@RequestParam(required = false) String name, ModelMap model, Principal principal) {
        model.addAttribute("message", "Hello:  " + " authenticatedUser: " + principal + (name != null ? name : "Anonymous") + " | " + BUNDLE.getString("userNotFound"));
        return "hello";
    }

    // for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {
        ModelAndView model = new ModelAndView();
        // check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);
            model.addObject("username", userDetail.getUsername());
        }
        model.setViewName("403");
        return model;

    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody LoginResponse login(@RequestBody LoginRequest request){
        clientService.login(request.getLoginUserInfo());
        return new LoginResponse();
    }


    //@Access(roles = {RoleUser.ADMIN})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    //@Secured({"ROLE_ADMIN"}) // "ROLE_REGULAR_USER"
    @RequestMapping(value = "/create", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody RestResponse create(Principal principal){

        clientService.create();
        RestResponse response = new RestResponse();
        response.setData(new Client("", "", "", ""));
        return response;
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        clientService.test();
        return "ok";
    }

    @RequestMapping(value = "/update", method = {RequestMethod.GET})
    @ResponseBody RestResponse update(){
        clientService.update(100L);
        return new RestResponse();
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody RestResponse clients(@RequestBody PageSearch filter){
        return clientService.list(filter);
    }


    @RequestMapping(value = "/id", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    ClientInfo client(@RequestBody PageSearch filter){
        Client client = clientService.getById(filter.getId());
        ClientInfo clientInfo = ModelUtil.toModel(client);
        return clientInfo;
    }


}
