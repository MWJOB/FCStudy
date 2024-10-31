package com.example.interceptor.config;

import com.example.interceptor.Interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor // final로 선언된 객체들을 생성자에게 주입 받을 수 있도록 해준다. @Autowired는 순환 참조가 될 수 있다.
public class MvcConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //내가 관리하고자 하는 주소를 넣을수 있다. exclude, addpath
        registry.addInterceptor(authInterceptor).addPathPatterns("/api/private/*");
        //인증의 과정도 depth 를 두고 확인 할 수 있다.
    }
}
