package org.example.bookmanager.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.example.bookmanager.domain.Member;
import org.example.bookmanager.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class EntityMangerTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void entityMangerTest() {
        System.out.println(entityManager.createQuery("select u from Member u").getResultList());
    }

    @Test
    void cacheFindTest(){
        Member member = new Member();
        member.setName("martin");
        member.setEmail("martin@naver.com");
        memberRepository.save(member);
//        System.out.println(memberRepository.findAll());
        System.out.println(">>>>>>>>>>>"+memberRepository.findById(1L).get());

        memberRepository.flush();

        System.out.println(">>>>>>>>>>>"+memberRepository.findById(1L).get());
//        System.out.println(memberRepository.findByEmail("martin@naver.com"));
//        System.out.println(memberRepository.findByEmail("martin@naver.com"));
//        System.out.println(memberRepository.findByEmail("martin@naver.com"));
    }
}
