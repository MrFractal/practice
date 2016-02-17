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
 * 1. ����� ������������������ ������������ �������� ����� �� ����������� ��������, ������ ���������� ��� �� ��������
 * ��� ����� ������ � ������. � ������ � ������ (��� ������) ��������� ���� �� ������, ��� ����� � ������� ����� RestAuthenticationEntryPoint
 * 2. ���� ������������ �������������, ������ ����� �������� �������� ������������ ���� ������������ � ���� �������, � ������
 * ���� ������������ �� �������� ����������� ����� ��� �������, �����������: RestAuthenticationAccessDeniedHandler
 *
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    // ���� �������� ���, ��� �� ������ �����������

    //@Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        System.out.println("RestAuthenticationEntryPoint");
        //throw new UserNotFound();

        response.getWriter().print("{\"error\":\"Unauthorized!\"}");
        response.getWriter().flush();

        //response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
}