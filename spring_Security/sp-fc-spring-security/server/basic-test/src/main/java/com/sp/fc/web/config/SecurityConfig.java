package com.sp.fc.web.config;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
public class SecurityConfig {


    private final CustomAuthDetails customAuthDetails;

    public SecurityConfig(CustomAuthDetails customAuthDetails) {
        this.customAuthDetails = customAuthDetails;
    }


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
                .password(passwordEncoder().encode("2222"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user2, user3);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // 루트와 오류 페이지는 모두에게 공개. /login은 매핑하지 않아 기본 로그인 페이지가 생성됨.
                        .requestMatchers("/", "/error","/index").permitAll()
                        .anyRequest().authenticated()
                )
                // 기본 로그인 페이지 사용. .loginPage()를 호출하지 않으므로 Spring Security가 기본 로그인 페이지를 생성합니다.
                .formLogin(form -> form.defaultSuccessUrl("/", false)
                        .authenticationDetailsSource(customAuthDetails))
                .logout(logout -> logout.logoutSuccessUrl("/").permitAll())
                // 테스트를 위해 CSRF 보호 비활성화 (실서비스에서는 활성화 권장)
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}