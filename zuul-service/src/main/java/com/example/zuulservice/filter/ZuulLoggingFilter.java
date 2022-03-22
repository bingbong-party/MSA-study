package com.example.zuulservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class ZuulLoggingFilter extends ZuulFilter {

    /**
     실제로 어떤 동작을 하는지 정의하는 메소드
     */
    @Override
    public Object run() throws ZuulException {
        log.info("************* printing logs: ");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info("************* " + request.getRequestURI()); //사용자가 어떠한 URI로 요청했는 지 알 수 있음

        return null;
    }

    /**
    필터를 사전에 처리할 지, 사후에 처리할 지 결정하는 메소드
    : 사전필터일 경우 pre를 return
    **/
    @Override
    public String filterType() {
        return "pre";
    }

    /**
    여러 개의 필터가 있을 경우, 순서를 정하는 메소드
    **/
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     필터로 쓸지 안쓸지 결정하는 메소드
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }
}
