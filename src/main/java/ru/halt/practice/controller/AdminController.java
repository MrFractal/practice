package ru.halt.practice.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.halt.practice.util.RestResponse;

/**
 * Created by Petr Rudenko on 11.02.2016.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public @ResponseBody
    RestResponse test() {
        RestResponse response = new RestResponse<String>();
        response.setData("Welcome!!!");
        return response;
    }
}
