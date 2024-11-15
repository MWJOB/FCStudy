package org.example.bookmanager.domain;


import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@EntityListeners(value = {MemberEntityListener.class})
public class Member extends BaseEntity implements Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

//    @CreatedDate
//    private LocalDateTime createdAt;
//
//    @Column
//    @LastModifiedDate
//    private LocalDateTime updatedAt;

//    //insert 메서드가 호출되기 전에 실행
//    @PrePersist
//    public void PrePersist() {
//        System.out.println(">>>prePersist");
//    }
//
//    //Delete 메서드가 호출되기 전 실행
//    @PreRemove
//    public void preRemove() {
//        System.out.println(">>>prePersist");
//    }
//
//    //Merge 메서드가 호출되기 전에 실행
//    @PreUpdate
//    public void preUpdate() {
//        System.out.println(">>>preUpdate");
//    }
//
//    //persist 메서드가 실행된 이후 실행
//    @PostPersist
//    public void postPersist() {
//        System.out.println(">>>postPersist");
//    }
//
//    //Merge 메서드가 호출된 이후에 실행
//    @PostUpdate
//    public void postUpdate() {
//        System.out.println(">>>postUpdate");
//    }
//
//    @PostRemove
//    public void postRemove() {
//        System.out.println(">>>postRemove");
//    }
//
//    @PostLoad
//    public void postLoad() {
//        System.out.println(">>>postLoad");
//    }
}
