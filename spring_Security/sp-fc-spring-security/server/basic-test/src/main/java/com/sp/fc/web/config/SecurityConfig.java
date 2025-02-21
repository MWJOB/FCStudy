package com.sp.fc.web.config;

import com.sp.fc.web.student.StudentManager;
import com.sp.fc.web.teacher.TeacherManager;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public AuthenticationManager authenticationManager(TeacherManager teacherManager, StudentManager studentManager) {
        return new ProviderManager(List.of(teacherManager, studentManager)); // ✅ 두 개의 Provider 등록
    }

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager, TeacherManager teacherManager, StudentManager studentManager) throws Exception {
    http
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/", "/login").permitAll()
                    .requestMatchers("/", "/login", "/css/**", "/js/**", "/images/**").permitAll() // ✅ 정적 리소스 허용
                    .anyRequest().authenticated()
            )
            .addFilterBefore(new CustomLoginFilter(authenticationManager, teacherManager, studentManager), UsernamePasswordAuthenticationFilter.class) // ✅ TeacherManager 주입
            .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // ✅ 필요 시 세션 관리 설정
            )
            .formLogin(form -> form
                    .loginPage("/login")
                    .loginProcessingUrl("/process-login")
                    .defaultSuccessUrl("/", true)
                    .permitAll()
            )
            .csrf(csrf -> csrf.disable());

    return http.build();
}



    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("student")
                .password(passwordEncoder.encode("1234"))
                .roles("STUDENT")
                .build();

        UserDetails teacher = User.withUsername("teacher")
                .password(passwordEncoder.encode("1234"))
                .roles("TEACHER")
                .build();

        return new InMemoryUserDetailsManager(user, teacher);
    }
}

