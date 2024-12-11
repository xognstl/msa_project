package com.example.userservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
//@AllArgsConstructor // args 를 다가지고 있는 생성자 생성
//@NoArgsConstructor // default 생성자
public class Greeting {

    @Value("${greeting.message}")
    private String message;

}
