package com.ljm.security.config;

import com.ljm.security.handler.JSONAuthenticationSuccessHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private JSONAuthenticationSuccessHandler successHandler = new JSONAuthenticationSuccessHandler();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
	        .successHandler(successHandler)
	        .loginPage("/login.html")
	        .loginProcessingUrl("/login")
	        .and()
	        .authorizeRequests()
	        .antMatchers("/login.html").permitAll()
	        .anyRequest()
	        .authenticated()
	        .and()
	        .csrf().disable();
    }
}
