package ru.halt.practice.security.zzz;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import ru.halt.practice.exception.UserNotFound;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Petr Rudenko on 11.02.2016.
 *
 * 1. когда неавторизированный пользователь пытается зайти на запрещенную страницу, спринг редиректит его на страницу
 * для ввода логина и пароля. в случае с рестом (наш случай) редиректа быть не должно, для этого и создаем класс RestAuthenticationEntryPoint
 * 2. если пользователь авторизирован, вторым шагом является проверка соответствия роли пользователя и роли ресурса, в случае
 * если пользователь не обладает необходимой ролью для доступа, срабатывает: RestAuthenticationAccessDeniedHandler
 *
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    // сюда попадают все, кто не прошел авторизацию

    //@Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        System.out.println("RestAuthenticationEntryPoint");
        //throw new UserNotFound();

        response.getWriter().print("{\"error\":\"Unauthorized!\"}");
        response.getWriter().flush();

        //response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
}