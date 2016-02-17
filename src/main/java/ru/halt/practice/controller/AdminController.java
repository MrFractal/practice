package ru.halt.practice.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Petr Rudenko on 11.02.2016.
 */
@Controller
@RequestMapping("/admin")
@Secured("ROLE_ADMIN")
public class AdminController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "ok";
    }
}
