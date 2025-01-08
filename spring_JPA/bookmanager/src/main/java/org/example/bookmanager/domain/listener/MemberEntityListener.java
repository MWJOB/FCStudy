package org.example.bookmanager.domain.listener;

import jakarta.persistence.PreUpdate;
import org.example.bookmanager.domain.Member;
import org.example.bookmanager.domain.MemberHistory;
import org.example.bookmanager.repository.MemberHistoryRepository;
import org.example.bookmanager.support.BeanUtils;
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
        memberHistory.setName(member.getName());
        memberHistory.setEmail(member.getEmail());
        memberHistory.setMember(member);
        memberHistory.setHomeAddress(member.getHomeAddress());
        memberHistory.setCompanyAddress(member.getCompanyAddress());

        memberHistoryRepository.save(memberHistory);
    }
}
