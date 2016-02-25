package ru.halt.practice.util;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Petr Rudenko on 31.01.2016.
 */
public class Utils {
    public static RestTemplate template() {
        RestTemplate template = new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new MappingJackson2HttpMessageConverter());
        converters.add(new StringHttpMessageConverter());
        template.setMessageConverters(converters);
        //template.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        template.setRequestFactory(new SimpleClientHttpRequestFactory());
        return template;
    }
}

/*
        RestTemplate template = new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        final ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        converters.add(new StringHttpMessageConverter());
        converter.setObjectMapper(objectMapper);
        converters.add(converter);

        template.setMessageConverters(converters);
        return template;



RestTemplate template = new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new MappingJackson2HttpMessageConverter());
        converters.add(new StringHttpMessageConverter());
        template.setMessageConverters(converters);
        //template.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        template.setRequestFactory(new SimpleClientHttpRequestFactory());
        return template;
        */
