package org.example.swagger.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReq {

    @Schema(description = "이름", example = "steve")
    private String name;

    @Schema(description = "나이", example = "25")
    private int age;
}
