package com.example.interceptor.Interceptor;

import com.example.interceptor.annotation.Auth;
import com.example.interceptor.exception.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURI();
        URI uri = UriComponentsBuilder.fromUriString(request.getRequestURI())
                .query(request.getQueryString()).build().toUri();

        log.info("request url: {}", url);

        //권한체크
        boolean hasAnnotation = checkAnnotation(handler, Auth.class);
        log.info("has annotation: {}", hasAnnotation);

        //나의 서버는 모두 public으로 동작하는데
        // 단 Auth 권한을 가진 요청에 대해서는 세션, 쿠키를 확인하겠다.
        if (hasAnnotation) {
            //권한 체크
            String query = uri.getQuery();
            if(query.equals("name=steve")){
                return true;
            }
            throw new AuthException();
            // 권한이 없으면 쓰로우를 시키고
            // handler가 받아서
            // 401을 내린다
            // 권한 같은 것들을 인터셉트가 확인하고 어노테이션과 핸들러는 처리가 가능하지만 필터는 불가능하다
            // 인터셉트는 스프링 컨텍스트에서 관리되고 있다.

        }

        return true;
    }

    private boolean checkAnnotation(Object handler, Class clazz) {

        //resource javascript, html
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        //annotation check
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if (null != handlerMethod.getMethodAnnotation(clazz) || null != handlerMethod.getBeanType().getAnnotations()) {
            // Auth annotation 이 있을때는 true
            return true;
        }
        return false;
    }
}
