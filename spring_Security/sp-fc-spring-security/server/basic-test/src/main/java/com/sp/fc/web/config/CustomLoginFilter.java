package com.sp.fc.web.config;
import com.sp.fc.web.student.StudentManager;
import com.sp.fc.web.teacher.TeacherManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import java.io.IOException;

public class CustomLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final TeacherManager teacherManager;
    private final StudentManager studentManager;

    public CustomLoginFilter(AuthenticationManager authenticationManager, TeacherManager teacherManager, StudentManager studentManager) {
        this.authenticationManager = authenticationManager;
        this.teacherManager = teacherManager;
        this.studentManager = studentManager;

        setFilterProcessesUrl("/process-login"); // ✅ 로그인 요청 경로를 변경 (GET /login 방해 방지)
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        System.out.println("🔹 로그인 요청 도착 - Method: " + request.getMethod() + ", URL: " + request.getRequestURI());

        if (!request.getMethod().equals("POST")) {
            System.out.println("✅ GET 요청은 필터 통과");
            return null; // GET 요청은 필터를 통과
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // ✅ 현재 로그인 가능한 사용자 출력
        System.out.println("🔹 [현재 로그인 가능한 사용자]");
        studentManager.getAllUsers().forEach((id, user) -> {
            System.out.println("   - Username: " + user.getUsername() + ", Password: " + user.getPassword());
        });
        teacherManager.getAllUsers().forEach((id, user) -> {
            System.out.println("   - Username: " + user.getUsername() + ", Password: " + user.getPassword());
        });

        System.out.println("🔹 로그인 시도 - username: " + username + ", password: " + password);

        if (username == null || password == null) {
            System.out.println("🚨 로그인 요청에서 아이디 또는 비밀번호가 비어 있음");
            throw new IllegalArgumentException("아이디 또는 비밀번호가 비어 있습니다.");
        }

        // ✅ AuthenticationManager에서 인증 시도
        Authentication authRequest = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(authRequest);

        System.out.println("✅ 인증 결과: " + (authentication.isAuthenticated() ? "성공" : "실패"));

        return authentication;
    }



    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // ✅ SecurityContext 직접 설정
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);

        // ✅ 세션에도 저장 (로그인 상태 유지)
        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);

        // ✅ SecurityContext 유지 여부 확인
        Authentication authCheck = SecurityContextHolder.getContext().getAuthentication();
        if (authCheck == null || !authCheck.isAuthenticated()) {
            System.out.println("🚨 로그인 후 SecurityContext가 유지되지 않음");
        } else {
            System.out.println("✅ SecurityContext 유지됨 - 사용자: " + authCheck.getName());
            System.out.println("✅ 현재 사용자의 권한: " + authCheck.getAuthorities());
        }

        response.sendRedirect("/"); // ✅ 로그인 성공 후 메인 페이지로 이동
    }




    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        response.sendRedirect("/login?error"); // ✅ 로그인 실패 시 다시 로그인 페이지로 이동
    }
}
