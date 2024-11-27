package org.example.bookmanager.repository;

import org.example.bookmanager.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
//    Optional<Member> findByName(String name);
//    Set<Member> findByName(String name);
    Member findByEmail(String email);
    Member getByEmail(String email);
    Member readByEmail(String email);
    Member queryByEmail(String email);
    Member searchByEmail(String email);
    Member streamByEmail(String email);
    Member findMemberByEmail(String email);

    List<Member> findFirstByName(String name);

    List<Member> findTopByName(String name);

    List<Member> findByEmailAndName(String email, String name);

    //시간
    List<Member> findByCreatedAtAfter(LocalDateTime createdAt);

    List<Member> findByIdAfter(Long id);

    List<Member> findByCreatedAtGreaterThan(LocalDateTime yesterday);

    List<Member> findByCreatedAtGreaterThanEqual(LocalDateTime yesterday);

    List<Member> findByIdIsNotNull();

//    List<Member> findByIdIsNotEmpty();

//    List<Member> findByNameIn(List<String> names);

    List<Member> findByNameStartingWith(String name);

    List<Member> findByNameEndingWith(String name);

    List<Member> findByNameContaining(String name);

    List<Member> findByNameLike(String name);

    List<Member> findTop1ByName(String name);

    //내림차순으로 정렬해서 맨위 하나만
    List<Member> findTop1ByNameOrderByIdDesc(String name);

    List<Member> findFirstByNameOrderByIdDesc(String name);

    List<Member> findFirstByName(String name, Sort sort);

    //페이징
    Page<Member> findByName(String name, Pageable pageable);
}
