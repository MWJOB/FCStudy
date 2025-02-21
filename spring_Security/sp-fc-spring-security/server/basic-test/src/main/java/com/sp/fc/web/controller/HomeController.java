package com.sp.fc.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            System.out.println("🚨 로그인된 사용자가 없음");
            model.addAttribute("message", "로그인되지 않았습니다.");
        } else {
            System.out.println("✅ 현재 로그인된 사용자: " + authentication.getName());
            System.out.println("✅ 현재 사용자의 권한: " + authentication.getAuthorities());
            model.addAttribute("username", authentication.getName());
        }

        return "index"; // ✅ 홈 페이지 반환
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

//    @GetMapping("/")
//    public String home(Authentication authentication) {
//        if (authentication != null) {
//            System.out.println("✅ 현재 로그인된 사용자: " + authentication.getName());
//            System.out.println("✅ 현재 사용자의 권한: " + authentication.getAuthorities());
//        } else {
//            System.out.println("🚨 로그인된 사용자가 없음");
//        }
//        return "index"; // ✅ 홈 페이지 반환
//    }
}
