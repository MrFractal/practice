
package ru.halt.practice.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.halt.practice.exception.ExceptionJSONInfo;
import ru.halt.practice.exception.UserNotFound;
import javax.servlet.http.HttpServletRequest;


/**
 * Created by Petr Rudenko on 09.02.2016.
 */


@ControllerAdvice
public class GlobalExceptionHandler  { // extends ResponseEntityExceptionHandler
    //private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("i18n.messages", Locale.getDefault());

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionJSONInfo> commonException(Exception exception, HttpServletRequest request) {
        exception.printStackTrace();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<ExceptionJSONInfo>(new ExceptionJSONInfo(exception.getMessage(), request.getRequestURL().toString()), headers, HttpStatus.OK);
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ExceptionJSONInfo> userNotFound(UserNotFound userNotFound, HttpServletRequest request){
        userNotFound.printStackTrace();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<ExceptionJSONInfo>(new ExceptionJSONInfo(userNotFound.getMessage(), request.getRequestURL().toString()), headers, HttpStatus.OK);
    }


    /*
    @ExceptionHandler(AccessException.class)
    public ModelAndView accessDeniedException() {
        ModelAndView model = new ModelAndView();
        model.addObject("exception", "");
        model.setViewName("accessDenied");
        return model;
    }
    */

}

