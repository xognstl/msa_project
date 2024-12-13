package com.example.userservice.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity // web security 용도로 사용할 것이다.
public class WebSecurity extends WebSecurityConfigurerAdapter {
    @Override   // 권한
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/users/**").permitAll();
        //authorizeRequests : 허용할 수 있는 작업을 제약 , /users/** 를 가져야만 통과

        http.headers().frameOptions().disable();    // 이게 있어야 h2-console 접근 가능
    }
}
