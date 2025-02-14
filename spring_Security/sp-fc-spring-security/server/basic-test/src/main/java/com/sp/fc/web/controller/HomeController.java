package com.sp.fc.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    // "/"와 "/index" 경로 모두 index.html 템플릿을 렌더링합니다.
    @GetMapping({"/", "/index"})
    public String showIndex() {
        return "index";  // src/main/resources/templates/index.html 파일을 렌더링
    }


    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @RequestMapping("/user")
    public SecurityMessage user(){
        return SecurityMessage.builder().auth(SecurityContextHolder
                .getContext()
                .getAuthentication())
                .message("User 정보")
                .build();
    }

    @ResponseBody
    @GetMapping("/auth")
    public Authentication auth(){
        return SecurityContextHolder.getContext().getAuthentication();
    }



    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @RequestMapping("/admin")
    public SecurityMessage admin(){
        return SecurityMessage.builder().auth(SecurityContextHolder
                .getContext()
                .getAuthentication())
                .message("관리자 정보")
                .build();
    }
}
