package org.example.bookmanager.repository;

import jakarta.transaction.Transactional;
import org.assertj.core.util.Lists;
import org.example.bookmanager.domain.Gender;
import org.example.bookmanager.domain.Member;
import org.example.bookmanager.domain.MemberHistory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberHistoryRepository memberHistoryRepository;

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
    void pagingAndSortingTest(){
        System.out.println("finalTop1ByName" + memberRepository.findTop1ByName("martin"));
        System.out.println("findLast1ByName" + memberRepository.findTop1ByNameOrderByIdDesc("martin"));
        System.out.println("findFirstByName" + memberRepository.findFirstByNameOrderByIdDesc("martin"));
        System.out.println("findFirstByNameWirthSortParams :" + memberRepository.findFirstByName("martin",
                Sort.by(Sort.Order.desc("Id"), Sort.Order.asc("email"))));

        System.out.println("findByNameWithPaging : "+ memberRepository.findByName("martin",
                PageRequest.of(0,1,Sort.by(Sort.Order.desc("Id")))).getContent());
    }

    @Test
    void insertAndUpdateTest(){
        Member member = new Member();
        member.setName("martin");
        member.setEmail("martin@naver.com");

        memberRepository.save(member);

        Member member1 = memberRepository.findById(1L).orElseThrow(RuntimeException::new);
        member1.setName("marrrrrtin");

        memberRepository.save(member1);
    }

    @Test
    void listenerTest(){
        Member member = new Member();
        member.setEmail("martin2@naver.com");
        member.setName("martin");

        memberRepository.save(member);

        Member member1 = memberRepository.findById(1L).orElseThrow(RuntimeException::new);
        member1.setName("martin2");

        memberRepository.deleteById(4L);
    }

    @Test
    void preUpdateTest(){
        Member member = memberRepository.findById(1L).orElseThrow(RuntimeException::new);

        System.out.println("as is" + member);

        member.setName("martin22");
        memberRepository.save(member);

        System.out.println("to be" + memberRepository.findAll().get(0));
    }

    @Test
    void memberHistoryTest(){
        Member member = new Member();
        member.setEmail("martin2@naver.com");
        member.setName("martin-new22");

        memberRepository.save(member);

        member.setName("martin-new33");

        memberRepository.save(member);

        memberHistoryRepository.findAll().forEach(System.out::println);
    }

    @Test
    void userRelationTest(){
        Member member = new Member();
        member.setName("david");
        member.setEmail("david@naver.com");
        member.setGender(Gender.MALE);

        memberRepository.save(member);

        member.setName("daniel");
        memberRepository.save(member);

        member.setEmail("daniel@naver.com");
        memberRepository.save(member);

        memberHistoryRepository.findAll().forEach(System.out::println);

//        List<MemberHistory> result = memberHistoryRepository.findByMemberId(
//                memberRepository.findByEmail("daniel@naver.com").getId());

        List<MemberHistory> result = memberRepository.findByEmail("daniel@naver.com").getMemberHistories();
        result.forEach(System.out::println);
    }

    @Test
    void embedTest(){
        memberRepository.findAll().forEach(System.out::println);
    }
}