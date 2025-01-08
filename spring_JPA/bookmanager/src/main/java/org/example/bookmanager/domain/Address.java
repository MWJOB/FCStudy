package org.example.bookmanager.domain;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String city; //시
    private String district; //구
    private String detail;  //상세주소
    private String zipCode;  //우편번호
}
