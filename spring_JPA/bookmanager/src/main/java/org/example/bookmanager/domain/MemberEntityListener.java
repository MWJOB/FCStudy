package org.example.bookmanager.domain;

import jakarta.persistence.PreUpdate;
import org.example.bookmanager.repository.MemberHistoryRepository;
import org.example.bookmanager.support.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberEntityListener {

//    //필드에 injection 하는 것은 권장하지 않는 사용 방식이다
//    @Autowired
//    private MemberHistoryRepository memberHistoryRepository;

    @PreUpdate
    public void prePersistAndPreUpdate(Object entity) {
        MemberHistoryRepository memberHistoryRepository = BeanUtils.getBean(MemberHistoryRepository.class);

        //유저 엔티티가 생성, 수정됐을때 유저 히스토리에 동일한 데이터가 쌓이게 된다.
        Member member = (Member) entity;
        MemberHistory memberHistory = new MemberHistory();
        memberHistory.setMemberId(member.getId());
        memberHistory.setName(member.getName());
        memberHistory.setEmail(member.getEmail());

        memberHistoryRepository.save(memberHistory);
    }
}
