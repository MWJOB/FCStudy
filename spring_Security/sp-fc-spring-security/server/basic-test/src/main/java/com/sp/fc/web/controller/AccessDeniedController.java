package com.sp.fc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class AccessDeniedController {

    @GetMapping("/403")
    public String accessDenied() {
        return "error/403"; // ✅ 403.html 반환
    }
}

