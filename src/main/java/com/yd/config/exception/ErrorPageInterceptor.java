package com.yd.config.exception;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author hgs
 * @version ErrorPageInterceptor.java, v 0.1 2018/03/04 20:52 hgs Exp $
 * <p>
 * 错误页面拦截器替代EmbeddedServletContainerCustomizer
 * 错误code拦截，由于使用Spring-Security，403全局异常拦截器不起作用,所以拦截403错误码，为了给前端返回json
 * 在war中不起作用的方法
 */
@Component
public class ErrorPageInterceptor extends HandlerInterceptorAdapter {
    private List<Integer> errorCodeList = Arrays.asList(403);//, 500, 501
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
        Exception {
       if (errorCodeList.contains(response.getStatus())) {
            response.sendRedirect("/error/" + response.getStatus());
            return false;
        }
        return super.preHandle(request, response, handler);
    }
    
}