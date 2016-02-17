package ru.halt.practice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.halt.practice.security.ClientAuthenticationProvider;
import ru.halt.practice.security.zzz.RestAuthenticationAccessDeniedHandler;
import ru.halt.practice.security.zzz.RestAuthenticationSuccessHandler;
import ru.halt.practice.security.zzz.RestAuthenticationEntryPoint;
import ru.halt.practice.security.zzz.HttpLogoutSuccessHandler;

/**
 * Created by Petr Rudenko on 10.02.2016.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true) // add 11.02.16
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private ClientAuthenticationProvider clientAuthenticationProvider;
    @Autowired
    @Qualifier("clientDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;
    @Autowired
    private RestAuthenticationAccessDeniedHandler restAuthenticationAccessDeniedHandler;
    //@Autowired
    //private HttpLogoutSuccessHandler logoutSuccessHandler;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        // some changes for check git repo

        auth.userDetailsService(userDetailsService); // .passwordEncoder(passwordEncoder())
        //auth.authenticationProvider(customAuthenticationProvider);


        //auth.authenticationProvider(clientAuthenticationProvider).
        //authenticationProvider(backendAdminUsernamePasswordAuthenticationProvider()).
        //authenticationProvider(tokenAuthenticationProvider());

        /*
        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("superadmin").password("superadmin").roles("SUPERADMIN");
        */
    }



//    @Bean
//    public RestUsernamePasswordAuthenticationFilter restFilter() throws Exception {
//        RestUsernamePasswordAuthenticationFilter myFilter = new RestUsernamePasswordAuthenticationFilter();
//        myFilter.setAuthenticationManager(authenticationManager());
//
//        return myFilter;
//    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").authenticated(); // .antMatchers(HttpMethod.POST, "/admin/**").authenticated();
                /*
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**").authenticated()
                .and()
                .formLogin()
                .successHandler(restAuthenticationSuccessHandler)
                .failureHandler(restAuthenticationAccessDeniedHandler)
                .and()
                .logout();
                */


        //http.authorizeRequests().anyRequest().authenticated();
    }


    /*
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        //authenticationProvider.setPasswordEncoder(new ShaPasswordEncoder());
        return authenticationProvider;
    }
    */



//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        System.out.println("userDetailsService = " + userDetailsService);
//        auth.userDetailsService(userDetailsService);
//        auth.authenticationProvider(customAuthenticationProvider);
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        return encoder;
//    }

}
