package ru.halt.practice.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


/**
 * Created by Petr Rudenko on 25.01.2016.
 */
public class WebConfigInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {ApplicationConfig.class, PersistenceContext.class}; // , SecurityConfig.class
    }


//    @Override
//    protected Filter[] getServletFilters() {
//        return new Filter[]{new AuthFilterSecurity()};
//    }
}
