package org.example.bookmanager.repository;

import org.assertj.core.util.Lists;
import org.example.bookmanager.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void crud() {

        List<Member> members = memberRepository.findAllById(Lists.newArrayList(1L,3L,5L));

        members.forEach(System.out::println);
    }

    @Test
    void save(){
        Member member = Member.builder()
                .name("존 도우")
                .email("john@naver.com")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Member savedMember = memberRepository.save(member);
        List<Member> members = memberRepository.findAll();
        members.forEach(System.out::println);


        assertNotNull(savedMember.getId());
        System.out.println("Saved Member: " + savedMember);
    }

    @Test
    void flush(){
        Member member = Member.builder()
                .name("존 도우")
                .email("john@naver.com")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        memberRepository.saveAndFlush(member);
        memberRepository.findAll().forEach(System.out::println);
    }

    @Test
    void count(){
        long count = memberRepository.count();

        System.out.println(count);
    }

    @Test
    void exist(){
        boolean exist = memberRepository.existsById(1L);

        System.out.println(exist);
    }

    @Test
    void delete(){
        memberRepository.deleteAll();
        memberRepository.findAll().forEach(System.out::println);
    }

    @Test
    void delete2(){
        memberRepository.deleteAllInBatch();
        memberRepository.findAll().forEach(System.out::println);
    }

    @Test
    void pageTest(){
        Page<Member> members = memberRepository.findAll(PageRequest.of(1,3));

        System.out.println("page : " + members);
        System.out.println("size : " + members.getTotalElements());
        System.out.println("total : " + members.getNumberOfElements());
    }
    //Query by Example
    @Test
    void QBE(){
        Member memberProbe = new Member();
        memberProbe.setName("ma");
        memberProbe.setEmail("naver.com");

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith())  // 이름이 "ma"로 시작하는 조건
                .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.endsWith());  // 이메일이 "naver.com"으로 끝나는 조건

        Example<Member> example2 = Example.of(memberProbe, matcher);
    }

    @Test
    void select(){
        System.out.println(memberRepository.findByName("martin"));


//        System.out.println("findByEmail : " + memberRepository.findByEmail("martin@naver.com"));
//        System.out.println("findByEmail : " + memberRepository.getByEmail("martin@naver.com"));
//        System.out.println("findByEmail : " + memberRepository.readByEmail("martin@naver.com"));
//        System.out.println("findByEmail : " + memberRepository.queryByEmail("martin@naver.com"));
//        System.out.println("findByEmail : " + memberRepository.searchByEmail("martin@naver.com"));
//        System.out.println("findByEmail : " + memberRepository.streamByEmail("martin@naver.com"));
//        System.out.println("findByEmail : " + memberRepository.findMemberByEmail("martin@naver.com"));
//
//        System.out.println("findTop1ByName : " + memberRepository.findTopByName("martin"));
//        System.out.println("findFirst1ByName : " + memberRepository.findFirstByName("martin"));
//        System.out.println("findByEmailAndName :" + memberRepository.findByEmailAndName("martin", "martin"));
        System.out.println("findByIdAfter :" + memberRepository.findByIdAfter(4L));
        System.out.println("findByCreatedAtGreaterThan : " + memberRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByCreatedAtGreaterTanEqual " + memberRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)));

    }
}