package com.ljm.security.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 让spring security验证我们自己的user数据
 */
@Slf4j
@Component
public class MyUserDetailsService implements UserDetailsService {


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		log.info(String.format("用户%s登录", username));
		return new User(username, passwordEncoder().encode("123456"), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}
}
