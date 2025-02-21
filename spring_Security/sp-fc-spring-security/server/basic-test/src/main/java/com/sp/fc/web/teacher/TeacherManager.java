package com.sp.fc.web.teacher;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class TeacherManager implements AuthenticationProvider, InitializingBean {

    private final HashMap<String, Teacher> teacherDB = new HashMap<>();
    private final PasswordEncoder passwordEncoder;

    public Map<String, Teacher> getAllUsers() {
        return teacherDB;
    }

    public TeacherManager(PasswordEncoder passwordEncoder) { // 생성자로 주입
        this.passwordEncoder = passwordEncoder;
    }
    @PostConstruct
    public void init(){
        Set<Teacher> teachers = new HashSet<>();
        teachers.add(new Teacher("KimTeacher", passwordEncoder.encode("1234"), Set.of(new SimpleGrantedAuthority("ROLE_TEACHER"))));
        teachers.add(new Teacher("LeeTeacher", passwordEncoder.encode("1234"), Set.of(new SimpleGrantedAuthority("ROLE_TEACHER"))));


        for (Teacher teacher : teachers) {
            teacherDB.put(teacher.getUsername(), teacher); // ✅ Null 체크
            System.out.println("✅ 저장된 계정: " + teacher.getUsername() + " / " + teacher.getPassword());
        }

        System.out.println("✅ Teacher 데이터 초기화 완료: " + teacherDB.keySet());
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("🔹 TeacherManager.authenticate() 호출됨");

        if (!(authentication instanceof UsernamePasswordAuthenticationToken)) {
            System.out.println("🚨 잘못된 인증 요청");
            return null;
        }

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        String username = token.getName();
        String rawPassword = token.getCredentials().toString();

        System.out.println("🔹 로그인 시도 - username: " + username + ", password: " + rawPassword);
        System.out.println("🔹 teacherDB 현재 키 목록: " + teacherDB.keySet()); // ✅ 저장된 키 확인

        if (teacherDB.containsKey(username)) {
            Teacher teacher = teacherDB.get(username);

            System.out.println("✅ 저장된 비밀번호 (해싱된 값): " + teacher.getPassword());

            if (passwordEncoder.matches(rawPassword, teacher.getPassword())) {
                System.out.println("✅ 비밀번호 일치: 로그인 성공!");
                return new UsernamePasswordAuthenticationToken(
                        teacher,
                        teacher.getPassword(),
                        teacher.getRole()
                );
            } else {
                System.out.println("🚨 비밀번호 불일치");
                throw new BadCredentialsException("비밀번호가 올바르지 않습니다.");
            }
        }

        System.out.println("🚨 사용자 없음: " + username);
        return null;
    }






    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        init();
//                Set.of(
//                new Teacher("KimTeacher", passwordEncoder.encode("1234"), Set.of(new SimpleGrantedAuthority("ROLE_TEACHER"))),
//                new Teacher("LeeTeacher", passwordEncoder.encode("1234"), Set.of(new SimpleGrantedAuthority("ROLE_TEACHER")))
//        ).forEach(s -> teacherDB.put(s.getId(), s));
//        teachers.add(new Teacher("KimTeacher", passwordEncoder.encode("1234"), Set.of(new SimpleGrantedAuthority("ROLE_TEACHER"))));
//        teachers.add(new Teacher("LeeTeacher", passwordEncoder.encode("1234"), Set.of(new SimpleGrantedAuthority("ROLE_TEACHER"))));

    }
}

