package org.example.bookmanager.service;

import jakarta.persistence.EntityManager;
import org.example.bookmanager.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserService userService;

    @Autowired
    private MemberRepository memberRepository;
    @Test
    void test(){
        userService.put();
        System.out.println(memberRepository.findByEmail("new@email.com"));

    }

}