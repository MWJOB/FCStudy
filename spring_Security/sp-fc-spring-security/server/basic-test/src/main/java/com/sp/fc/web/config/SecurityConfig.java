package com.sp.fc.web.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.config.Customizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        // 테스트용 사용자 2명
        UserDetails user2 = User
                .withUsername("user2")
                .password(passwordEncoder().encode("1111"))
                .roles("USER")
                .build();

        UserDetails user3 = User
                .withUsername("user3")
                .password("{noop}2222")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user2, user3);
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        // "/" 경로만 인증 요구
//                        .requestMatchers("/").authenticated()
//                        // 그 외의 경로들은 일단 모두 허용
//                        .anyRequest().permitAll()
//                )
//                // 인증 필요 경로에 접근 시 스프링 시큐리티 기본 로그인 폼으로 이동
//                .formLogin(Customizer.withDefaults())
//                // 테스트용 CSRF 비활성화 (필요 시 활성화)
//                .csrf(csrf -> csrf.disable());
//
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // (1) "/"만 누구나 접근 가능
                        .requestMatchers("/").permitAll()
                        // (2) 나머지 요청은 인증 필요
                        .anyRequest().authenticated()
                )
                // 인증이 필요한 경로 접근 시 기본 로그인 폼으로 이동
                .formLogin(Customizer.withDefaults())

                // 테스트 편의를 위해 CSRF 비활성화(실서비스는 활성화 권장)
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}