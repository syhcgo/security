package com.ljm.security.config;

import com.ljm.security.filter.SmsCodeSimpleAuthenticationFilter;
import com.ljm.security.handler.JSONAuthenticationSuccessHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private JSONAuthenticationSuccessHandler successHandler = new JSONAuthenticationSuccessHandler();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SmsCodeSimpleAuthenticationFilter smsCodeSimpleAuthenticationFilter = new SmsCodeSimpleAuthenticationFilter();
        smsCodeSimpleAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsCodeSimpleAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);

        http.addFilterAfter(smsCodeSimpleAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .formLogin()
            .successHandler(successHandler)
            .loginPage("/login.html")
            .loginProcessingUrl("/login")
            .and()
            .authorizeRequests()
            .antMatchers("/login.html", "/sms-code/send", "/sms-code/login").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .csrf().disable();
    }
}
