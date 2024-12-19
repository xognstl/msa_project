package com.example.apigatewayservice.filter;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    Environment env;

    public AuthorizationHeaderFilter(Environment env) {
        super(Config.class);
        this.env = env;
    }

    // 로그인 -> 토큰 받음 -> user(토큰 정보가지고 요청) -> header(include token)
    // API 를 호출할 때 사용자가 헤더에 토큰을 전달해주는 작업
    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            if(!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){   // AUTHORIZATION 관련된 값 존재 유무
                return onError(exchange, "no Authorization header", HttpStatus.UNAUTHORIZED);
            }

            String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);    // 토큰 값 가져오기
            String jwt = authorizationHeader.replace("Bearer", "");
            
            if (!isJwtValid(jwt)) { // Jwt token valid
                return onError(exchange, "JWT token is not valid", HttpStatus.UNAUTHORIZED);
            }
            
            return chain.filter(exchange);
        });
    }

    // jwt decode 했을때 Subject 를 토큰으로 부터 추출한 후 값이 정상적인 계정 값인지 판단
    private boolean isJwtValid(String jwt) {
        boolean returnValue = true;

        String subject = null;
        try {
            System.out.println(env.getProperty("token.secret"));
            subject = Jwts.parser().setSigningKey(env.getProperty("token.secret"))
                    .parseClaimsJws(jwt).getBody()
                    .getSubject();  // 토큰을 문자형 데이터 값으로 파싱하기 위해 parseClaimsJws로 파싱 , subject 값만 추출
        } catch (Exception exception) {
            returnValue = false;
        }

        if (subject == null || subject.isEmpty()) {
            returnValue = false;
        }

        return returnValue;
    }

    // Mono(단일), Flux(다중)
    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();   // response 객체 받아옴
        response.setStatusCode(httpStatus);

        log.error(err);

        return response.setComplete();
    }
    

    public static class Config {

    }
}
