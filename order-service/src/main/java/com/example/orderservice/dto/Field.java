package com.example.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

// 이 값은 임의로 지정하는 것이 아니라 kafka connector 를 통해 소스 커텍터에서 싱크 커넥터로 데이터를 보낼때 어떤 정보가 저장 될 것인지 필드를 구성해야 한다.
@Data
@AllArgsConstructor
public class Field {    // 데이터를 저장하기 위해 어떤 필드가 사용될 것인지 지정

    private String type;
    private boolean optional;
    private String field;
}
