package ru.halt.practice.util;

import java.util.Random;

/**
 * Created by Petr Rudenko on 19.02.2016.
 */
public class TokenUtil {
    public static String generateToken(String login, String password){
        String token = login + ":" + password + ":" + new Random().nextInt(17);
        System.out.println("token ========== " + token);
        return token;
    }
}
