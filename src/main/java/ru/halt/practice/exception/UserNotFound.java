package ru.halt.practice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Petr Rudenko on 09.02.2016.
 */
@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class UserNotFound extends RuntimeException {
    public UserNotFound(){
    }
    public UserNotFound(String message){
        super(message);
    }
}
