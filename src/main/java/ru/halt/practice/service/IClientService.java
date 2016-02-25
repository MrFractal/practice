package ru.halt.practice.service;

import ru.halt.practice.domain.AbstractEntity;
import ru.halt.practice.domain.IUser;
import ru.halt.practice.rest.LoginRequest;
import ru.halt.practice.rest.LoginResponse;
import ru.halt.practice.rest.PageSearch;
import ru.halt.practice.util.LoginUserInfo;
import ru.halt.practice.util.RestResponse;

/**
 * Created by Petr Rudenko on 09.02.2016.
 */
public interface IClientService<T extends AbstractEntity> {
    void create();
    void update(Long id);
    void delete();
    RestResponse list(PageSearch filter);
    void test();
    String login(String login, String password);
    T getById(Long id);
}
