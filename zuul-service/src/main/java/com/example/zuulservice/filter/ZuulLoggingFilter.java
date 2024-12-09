package com.example.zuulservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@Component
public class ZuulLoggingFilter extends ZuulFilter {

//    Logger logger = LoggerFactory.getLogger(ZuulLoggingFilter.class);
    // lombok @Slf4j

    @Override
    public Object run() throws ZuulException {  //실제동작
        log.info("************************ printing logs : ");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest(); //servlet 에서 사용자 요청 정보 처리
        log.info("************************ " + request.getRequestURI());

        return null;
    }

    @Override
    public String filterType() {
        return "pre";   // 사전 필터 (사후는 post)
    }

    @Override
    public int filterOrder() {
        return 1;   // 순서
    }

    @Override
    public boolean shouldFilter() {
        return true;    //사용 여부
    }


}
