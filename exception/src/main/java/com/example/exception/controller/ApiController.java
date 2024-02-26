package com.example.exception.controller;


import com.example.exception.dto.User;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping()
    public User get(@RequestParam String name, @RequestParam int age){
        User user = new User();
        user.setAge(age);
        user.setName(name);

        return user;
    }

    @PostMapping("")
    public User post(@Valid @RequestBody User user){
        System.out.println(user);
        return user;
    }
}
