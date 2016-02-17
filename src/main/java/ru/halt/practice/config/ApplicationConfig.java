package ru.halt.practice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

/**
 * Created by Petr Rudenko on 30.01.2016.
 */
@Configuration
@PropertySource(value = { "classpath:profiles/dev.properties" })
@ComponentScan({"ru.halt.practice.service" , "ru.halt.practice.config", "ru.halt.practice.security"})
//@Import({ SecurityConfig.class })
public class ApplicationConfig {
    @Autowired
    private Environment env;

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
