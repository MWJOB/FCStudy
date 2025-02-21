package com.sp.fc.web.controller;

import com.sp.fc.web.teacher.Teacher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @GetMapping("/main")
    public String main(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            Teacher teacher = (Teacher) authentication.getPrincipal();

            // ✅ 보안상 비밀번호는 제외하고 전달
            model.addAttribute("teacherName", teacher.getUsername());
            model.addAttribute("teacherRole", teacher.getRole());
        }

        return "teacher/main"; // ✅ teacher 전용 페이지로 이동
    }
}