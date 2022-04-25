package me.kenux.springmvc.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@Order(1)
public class TestFilter1 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("TestFilter1");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        log.info("Method={}, Url={}", httpServletRequest.getMethod(), httpServletRequest.getRequestURL());
        chain.doFilter(request, response);
        HttpServletResponse resp = (HttpServletResponse) response;
        log.info("response status={}", resp.getStatus());
    }
}