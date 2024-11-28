package org.example.bookmanager.domain;


import jakarta.persistence.*;
import lombok.*;
import org.example.bookmanager.domain.listener.MemberEntityListener;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@EntityListeners(value = {MemberEntityListener.class})
public class Member extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(fetch = FetchType.EAGER)
    @Builder.Default
    private List<MemberHistory> memberHistories = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "member_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

}
