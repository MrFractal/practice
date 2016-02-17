package ru.halt.practice.domain;

import java.util.Collection;

/**
 * Created by Petr Rudenko on 11.02.2016.
 */
public interface IUser {
    String getLogin();
    String getPassword();
    boolean isEnabled();
    Collection<ClientRole> getClientRoles();
}
