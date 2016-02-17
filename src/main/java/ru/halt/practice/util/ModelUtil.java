package ru.halt.practice.util;

import ru.halt.practice.domain.Client;
import ru.halt.practice.rest.ClientInfo;

/**
 * Created by Petr Rudenko on 13.02.2016.
 */
public class ModelUtil {

    public static ClientInfo toModel(Client client){
        ClientInfo info =  new ClientInfo();
        info.setId(client.getId());
        info.setLogin(client.getLogin());
        info.setLogin(client.getPassword());
        return info;
    }
}
