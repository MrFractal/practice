package ru.halt.practice.service;

import ru.halt.practice.domain.AbstractEntity;
import ru.halt.practice.domain.IUser;

/**
 * Created by Petr Rudenko on 19.02.2016.
 */
public interface TokenService<T extends AbstractEntity> {
    String generateToken(Long id);
    boolean validateToken(String token);
    T getUserByToken(String token);
}
