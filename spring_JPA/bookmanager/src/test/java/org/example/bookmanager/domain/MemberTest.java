package org.example.bookmanager.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class MemberTest {
    @Test
    void test(){
        Member member = new Member();
        member.setEmail("mortis@naver.com");
        member.setName("marin");
        member.setCreatedAt(LocalDateTime.now());
        member.setUpdatedAt(LocalDateTime.now());

        System.out.println(member);

    }

}