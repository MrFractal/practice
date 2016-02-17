package ru.halt.practice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Petr Rudenko on 10.02.2016.
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class AccessException extends RuntimeException {
    public AccessException(String message) {
        super(message);
    }
}
