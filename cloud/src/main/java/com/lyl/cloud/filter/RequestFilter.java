package com.lyl.cloud.filter;

import com.lyl.cloud.wrapper.MyServletRequestWrapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("come in request filter");
        MyServletRequestWrapper myServletRequestWrapper = new MyServletRequestWrapper((HttpServletRequest) servletRequest);
        ServletInputStream inputStream = myServletRequestWrapper.getInputStream();
        byte[] bytes = new byte[1024];
        int len = inputStream.read(bytes);
        log.info("------:"+ new String(bytes, 0, len));
        filterChain.doFilter(myServletRequestWrapper, servletResponse);
    }
}
