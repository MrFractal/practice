package ru.halt.practice.security;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by Petr Rudenko on 06.02.2016.
 */
public class ImplApplicationContextAware implements ApplicationContextAware{
    ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    ApplicationContext getApplicationContext(){
        return applicationContext;
    }
}
