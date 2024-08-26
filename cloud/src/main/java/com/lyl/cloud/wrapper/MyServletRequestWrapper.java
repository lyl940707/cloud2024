package com.lyl.cloud.wrapper;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class MyServletRequestWrapper extends HttpServletRequestWrapper {

    private String body;

    public MyServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        if (StrUtil.isBlank(this.body)) {
            byte[] bytes = new byte[1024];
            int read = request.getInputStream().read(bytes);
            this.body = new String(bytes, 0, read);
        }
    }

    @Override
    public ServletInputStream getInputStream(){
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.body.getBytes(StandardCharsets.UTF_8));
        ServletInputStream servletInputStream = new ServletInputStream() {
            @Override
            public int read() throws IOException {
                int read1 = byteArrayInputStream.read();
                return read1;
            }

            @Override
            public boolean isFinished() {
                System.out.println(byteArrayInputStream.available());
                return byteArrayInputStream.available() == 0;
            }

            @Override
            public boolean isReady() {
                System.out.println("ture");
                // 总是返回true，‌表示流已经准备好被读取
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }
        };
        return servletInputStream;
    }

    @Override//对外提供读取流的方法
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

}
