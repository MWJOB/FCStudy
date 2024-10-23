package org.example.swagger.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.swagger.dto.UserReq;
import org.example.swagger.dto.UserRes;
import org.springframework.web.bind.annotation.*;

@Tag(name = "ApiController", description = "스웨거 확인")
@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/plus/{x}")
    public int plus(

            @Parameter(name = "실습 x값") @PathVariable int x,
            @Parameter(name = "실습2 y값") @RequestParam int y) {
        return x+y;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user information"),
            @ApiResponse(responseCode = "400", description = "Invalid userId supplied"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Operation(summary = "사용자의 이름과 나이를 리턴하는 메소드", description = "이름과 나이를 입력하세요!")
    @GetMapping("/user")
    public UserRes user(UserReq userReq){
       return new UserRes(userReq.getName(), userReq.getAge());
    }

    @PostMapping("/user")
    public UserRes userPost(@RequestBody UserReq req){
        return new UserRes(req.getName(), req.getAge());
    }
}
