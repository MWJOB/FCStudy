package com.example.filter.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.BufferedReader;
import java.io.IOException;

// 필터에서는 제일 최전방에서 들어오는 정보를 처리할 수 있고 session 같은 정보를 불러와서 로그아웃 및 401 에러를 유발 시키는 것과 같은 로직을 처리할 수 있다. 로깅 용도로 많이 사용한다.
@Slf4j
@WebFilter(urlPatterns = "/api/user/*") // url패턴을 넣어 특정 클래스 또는 특정 컨트롤러에 적용이 가능하다. 배열로도 가능
public class GlobalFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 전처리
        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper ((HttpServletRequest) request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper ((HttpServletResponse) response);

        chain.doFilter(httpServletRequest, httpServletResponse); //doFilter 이후에 찍어야 데이터가 정상적으로 읽혀진다.

        String url = httpServletRequest.getRequestURI();

        // 후처리, 모든 정보 기록하게 됨
        // req
        String reqContent = new String(httpServletRequest.getContentAsByteArray());
        log.info("response url : {}, requestBody : {}", url, reqContent);


        String resContent = new String(httpServletResponse.getContentAsByteArray());
        int httpStatus = httpServletResponse.getStatus();

        httpServletResponse.copyBodyToResponse(); // 읽은 값을 한 번 더 복사해주는 메소드

        log.info("response status : {}, responseBody : {}", httpStatus, resContent);

//        BufferedReader br = httpServletRequest.getReader();

//        br.lines().forEach(line -> {
//            log.info("url ; {}, line ; {}", url, line);
//        });


        // 필터에서 req res를 찍어야 하면 ContentCachingRequestWrapper, ContentCachingResponseWrapper 사용하고 doFilter 후 response 내용을 찍되 copyBodyToResponse() 메서드를 호출해야 정상적인 응답을 받을 수 있다.
    }
}
