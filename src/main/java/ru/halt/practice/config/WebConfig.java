package ru.halt.practice.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;

import java.util.List;

/**
 * Created by Petr Rudenko on 25.01.2016.
 */
@EnableWebMvc //mvc:annotation-driven
@Configuration
@ComponentScan({"ru.halt.practice.controller"})
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    // поставил это и ушла проблема:
    // PageNotFound - No mapping found for HTTP request with URI [/] in DispatcherServlet with name 'dispatcher'
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/index");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        converter.setObjectMapper(objectMapper);
        converters.add(converter);
        super.configureMessageConverters(converters);
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }


    /*
    @Bean
    public ResourceBundleViewResolver resourceBundleResolver() {
        ResourceBundleViewResolver resourceResolver = new ResourceBundleViewResolver();
        //resourceResolver.setBasenames(new String[]{"/i18n/"});
        resourceResolver.setBasename("/i18n/messages");
        return resourceResolver;
    }
    */


    /*
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("/i18/usermsg");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    */

}

