package ru.halt.practice.security;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Petr Rudenko on 06.02.2016.
 */
public abstract class AbstractAutFilterSecurity implements Filter {
//    private AutowireCapableBeanFactory autowireCapableBeanFactory;
//
//    public AbstractAutFilterSecurity(){
//    }
//
//    public AutowireCapableBeanFactory getAutowireCapableBeanFactory() {
//        return autowireCapableBeanFactory;
//    }
//
//    public void init(FilterConfig filterConfig) throws ServletException {
//        //LOGGER.info("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
//        System.out.println("INIT: " + filterConfig.getServletContext().getServletContextName());
//        ServletContext servletContext = filterConfig.getServletContext();
//        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
//        this.autowireCapableBeanFactory = webApplicationContext.getAutowireCapableBeanFactory();
//    }
//
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest)servletRequest;
//        String name = request.getUserPrincipal().getName();
//        System.out.println("Client name: " + name);
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    public void destroy() {
//
//    }
}
