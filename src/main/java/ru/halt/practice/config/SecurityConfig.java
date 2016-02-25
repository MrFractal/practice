package ru.halt.practice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ru.halt.practice.security.ClientAuthenticationProvider;
import ru.halt.practice.security.zzz.*;

/**
 * Created by Petr Rudenko on 10.02.2016.
 */


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) //
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private RestAuthenticationAccessDeniedHandler accessDeniedHandler;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myTokenAuthenticationProvider());
    }

    @Bean
    public MyTokenAuthenticationProvider myTokenAuthenticationProvider(){
        return new MyTokenAuthenticationProvider();
    }


    @Bean
    public TestFilter getTestFilter() throws Exception {
        return new TestFilter(authenticationManager());
    }

    // метро кузнецкий мост 18.45 - 19.00

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /*http
                .addFilterBefore(getTestFilter(), BasicAuthenticationFilter.class)
                .antMatcher("/admin*//**")
                .authenticationProvider(myTokenAuthenticationProvider())
                .authorizeRequests()
                .anyRequest().authenticated();*/


        http.
                csrf().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().
                authorizeRequests().
                antMatchers("/admin/**").hasRole("ADMIN").
                //anyRequest().authenticated().
                and().
                anonymous().disable().
                exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(accessDeniedHandler);

        http.addFilterBefore(getTestFilter(), BasicAuthenticationFilter.class);


        /*
        http.
                csrf().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().
                authorizeRequests().
                antMatchers("/admin/**").hasRole("ADMIN").
                anyRequest().authenticated().
                and().
                anonymous().disable().
                exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);

        http.addFilterBefore(getTestFilter(), BasicAuthenticationFilter.class);
        //http.addFilterBefore(new MyAuthFilter(authenticationManager(), userDetailsService), BasicAuthenticationFilter.class);
        //http.addFilterBefore(new AuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class);

        */
    }
}
