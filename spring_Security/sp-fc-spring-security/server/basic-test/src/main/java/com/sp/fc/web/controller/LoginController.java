package com.sp.fc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    // ✅ 로그인 페이지 요청 처리
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // login.html 반환
    }
}
