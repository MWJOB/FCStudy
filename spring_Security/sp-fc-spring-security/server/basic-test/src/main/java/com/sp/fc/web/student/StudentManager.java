package com.sp.fc.web.student;

import com.sp.fc.web.teacher.Teacher;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//AuthenticationProvider 를 AuthenticationManager 에 등록해줘야 한다, 그 동작은 SecurityConfig 에 등록하면 된다.

@Component
public class StudentManager implements AuthenticationProvider, InitializingBean {

    private final HashMap<String, Student> studentDB = new HashMap<>();

    private final PasswordEncoder passwordEncoder;

    public StudentManager(PasswordEncoder passwordEncoder) { // 생성자로 주입
        this.passwordEncoder = passwordEncoder;
    }
    public Map<String, Student> getAllUsers() {
        return studentDB;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!(authentication instanceof UsernamePasswordAuthenticationToken)) {
            return null;
        }

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        String username = token.getName();

        if (studentDB.containsKey(username)) {
            Student student = studentDB.get(username);

            // 비밀번호 검증이 필요하면 여기에 추가
            return new UsernamePasswordAuthenticationToken(
                    student,
                    student.getUsername(), // credentials에 비밀번호 넣기 (기본적으로 passwordEncoder 적용 필요)
                    student.getRole()
            );
        }

        return null;
    }



    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        Set.of(
//                new Student("hong", passwordEncoder.encode("1234"), Set.of(new SimpleGrantedAuthority("ROLE_STUDENT"))),
//                new Student("rang", passwordEncoder.encode("5678"), Set.of(new SimpleGrantedAuthority("ROLE_STUDENT")))
//        ).forEach(s -> studentDB.put(s.getId(), s));
    }
}
