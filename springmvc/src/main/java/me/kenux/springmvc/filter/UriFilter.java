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
@Order(0)
public class UriFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("UriFilter doFilter");
        final HttpServletRequest req = (HttpServletRequest) request;
        log.info("method={}, url={}",req.getMethod(), req.getRequestURL());
        chain.doFilter(request, response);
        HttpServletResponse resp = (HttpServletResponse) response;
        log.info("response status={}", resp.getStatus());
    }

}