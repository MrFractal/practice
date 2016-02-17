package ru.halt.practice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import ru.halt.practice.exception.AccessException;
import ru.halt.practice.exception.UserNotFound;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Petr Rudenko on 09.02.2016.
 */
@ControllerAdvice
public class GlobalExceptionHandler { // extends ResponseEntityExceptionHandler
    // добавить логирование всех ошибок
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("i18n.messages", Locale.getDefault());

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ExceptionJSONInfo commonException(Exception exception, HttpServletRequest request) {
        return new ExceptionJSONInfo(exception.getMessage(), request.getRequestURL().toString());
    }

    @ExceptionHandler(UserNotFound.class)
    @ResponseBody
    public ExceptionJSONInfo userNotFound(UserNotFound userNotFound, HttpServletRequest request){
        return new ExceptionJSONInfo(userNotFound.getMessage() != null ? userNotFound.getMessage() : BUNDLE.getString("userNotFound"), request.getRequestURL().toString());
    }
    //HttpServletRequest request1 = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();


    @ExceptionHandler(AccessException.class)
    public ModelAndView accessDeniedException() {
        ModelAndView model = new ModelAndView();
        model.addObject("exception", "");
        model.setViewName("accessDenied");
        return model;
    }


    private class ExceptionJSONInfo {
        private String message;
        private String url;

        public ExceptionJSONInfo(String message, String url){
            this.message = message;
            this.url = url;
        }

        public String getMessage() {
            return message;
        }

        public String getUrl() {
            return url;
        }
    }
}
