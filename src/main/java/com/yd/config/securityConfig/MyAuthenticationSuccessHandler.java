package com.yd.config.securityConfig;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 处理登录成功的。
 * @author FelixChen
 *
 */
@Component("myAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
      
      @Autowired
      private ObjectMapper objectMapper;
      @Override
      public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
                  throws IOException, ServletException {            
//    	  authentication.getPrincipal();//应该得到的是UserDetails(UserInfo是实现类)，有管理员的账号，密码等，未测试，但是觉得可以
            //什么都不做的话，那就直接调用父类的方法,该方法会直接重定向到之前被拦截的方法
            super.onAuthenticationSuccess(request, response, authentication);  
            //这里可以根据实际情况，来确定是跳转到页面或者json格式。
            //如果是返回json格式，那么我们这么写
//            Map<String,String> map=new HashMap<>();
//            map.put("code", "200");
//            map.put("msg", "登录成功");
//            response.setContentType("application/json;charset=UTF-8");
//            response.getWriter().write(objectMapper.writeValueAsString(map));
            
            //如果是要跳转到某个页面的，比如我们的那个index，二选一
//            new DefaultRedirectStrategy().sendRedirect(request, response, "/manager/index");
            
      }
      
}