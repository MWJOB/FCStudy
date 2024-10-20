package com.example.client.controller;

import com.example.client.dto.Req;
import com.example.client.dto.UserResponse;
import com.example.client.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class ApiController {


    //@Autowired는 옛날 방식 생성자 주입이나 lombok으로 많이 처리한다.
    private final RestTemplateService restTemplateService;

    public ApiController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @RequestMapping("/hello")
    public Req<UserResponse> getHello(){
        return restTemplateService.genericExchange();
    }
}
