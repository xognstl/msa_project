package com.example.secondservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SecondServiceController {
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome to the Second service";
    }
}
