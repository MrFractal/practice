package ru.halt.practice.security.zzz;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Petr Rudenko on 11.02.2016.
 *
 * если пользователь авторизирован, вторым шагом является проверка соответствия роли пользователя и роли ресурса, в случае
 * если пользователь не обладает необходимой ролью для доступа, срабатывает: RestAuthenticationAccessDeniedHandler
 */
@Component
public class RestAuthenticationAccessDeniedHandler implements AccessDeniedHandler { // or extends SimpleUrlAuthenticationFailureHandler   // AccessDeniedHandler

    //@Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.getWriter().print("{\"error\":\"Access denied!\"}");
        //httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }


    /*

    in case: extends SimpleUrlAuthenticationFailureHandler

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        System.out.println("In AuthFailureHandler");

        PrintWriter writer = response.getWriter();
        writer.write(exception.getMessage());
        writer.flush();
    }
   */
}
