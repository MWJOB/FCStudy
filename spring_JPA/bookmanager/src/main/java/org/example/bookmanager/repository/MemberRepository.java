package org.example.bookmanager.repository;

import org.example.bookmanager.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface MemberRepository extends JpaRepository<Member, Long> {
//    Optional<Member> findByName(String name);
    Set<Member> findByName(String name);
    Member findByEmail(String email);
    Member getByEmail(String email);
    Member readByEmail(String email);
    Member queryByEmail(String email);
    Member searchByEmail(String email);
    Member streamByEmail(String email);
    Member findMemberByEmail(String email);

    List<Member> findFirstByName(String name);
    List<Member> findTopByName(String name);
}
