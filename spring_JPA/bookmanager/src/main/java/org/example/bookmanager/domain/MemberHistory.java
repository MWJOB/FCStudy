package org.example.bookmanager.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MemberHistory extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String email;

    @Embedded
    private Address homeAddress;

    @Embedded
    private Address companyAddress;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne
    @ToString.Exclude
    private Member member;
}
