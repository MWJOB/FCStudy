package org.example.bookmanager.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
//@EntityListeners(value = AuditingEntityListener.class)
public class MemberHistory extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;

    private Long memberId;

    private String name;

    private String email;

//    private LocalDateTime createdAt;
//
//    private LocalDateTime updatedAt;
}
