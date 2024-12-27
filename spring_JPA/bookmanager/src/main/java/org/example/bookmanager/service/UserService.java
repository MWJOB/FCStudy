package org.example.bookmanager.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.example.bookmanager.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private EntityManager em;

    @Transactional
    public void put(){
        Member member = new Member();
        member.setName("new User");
        member.setEmail("new@email.com");

        em.persist(member);
    }
}
