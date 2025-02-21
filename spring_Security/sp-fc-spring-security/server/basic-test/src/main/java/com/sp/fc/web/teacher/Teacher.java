package com.sp.fc.web.teacher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teacher {

    private String id;
    private String username;
    private String password;
    private Set<GrantedAuthority> role;

    public Teacher(String username, String password, Set<GrantedAuthority> role) {
        this.id = UUID.randomUUID().toString(); // ✅ 랜덤 UUID 생성
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
