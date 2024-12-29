package com.demo.api.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.activation.DataSource;


@Configuration
/* @EnableWebSecurity */

public class WebSecurityConfig {
	/*
	 * @Autowired private DataSource dataSource;
	 */


@Bean
public UserDetailsService userDetailService() {
	return new CustomUserDetailsService();
	
	
}

@Bean
public BCryptPasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
	
}

@Bean
public DaoAuthenticationProvider authenticationProvider() {
	DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
	authProvider.setUserDetailsService(userDetailService());
	authProvider.setPasswordEncoder(passwordEncoder());
	return authProvider;
}

@Bean
SecurityFilterChain configure(HttpSecurity http) throws Exception {
     
    http.authenticationProvider(authenticationProvider());
     
    http.authorizeHttpRequests(auth ->
        auth.requestMatchers("/list_users").authenticated()
        .anyRequest().permitAll()
        )
        .formLogin(login ->
            login.usernameParameter("email")
            .defaultSuccessUrl("/list_users")
            .permitAll()
        )
        .logout(logout -> logout.logoutSuccessUrl("/").permitAll()
    );
     
    return http.build();
} 

}
