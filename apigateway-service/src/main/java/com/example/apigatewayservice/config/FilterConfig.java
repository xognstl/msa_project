package com.example.apigatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfig {

    //r 값이 전달되게 되면 패스를 확인 하고 필터적용해서 uri로 이동 시켜준다.
    // 사용자로부터 First service라는 요청이 들어온다.
    // 그러면 url 로 이동, 중간에 request filter addRequestHeader("first-request", "first-request-header")
    // response filter 에는  .addRequestHeader("first-response", "first-response-header") 를 추가한다.
    // yml에 있던 설정과 똑같은것을 자바로 한거다.
//    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/first-service/**")
                        .filters(f -> f.addRequestHeader("first-request", "first-request-header")
                                        .addResponseHeader("first-response", "first-response-header"))
                        .uri("http://localhost:8081"))
                .route(r -> r.path("/second-service/**")
                        .filters(f -> f.addRequestHeader("second-request", "second-request-header")
                                .addResponseHeader("second-response", "second-response-header"))
                        .uri("http://localhost:8082"))
                .build();
    }
}
