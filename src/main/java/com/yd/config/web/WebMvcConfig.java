package com.yd.config.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	private  HandlerInterceptorAdapter  errorPageInterceptor;
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(errorPageInterceptor);//错误code拦截，由于使用Spring-Security，全局异常拦截器不起作用
        super.addInterceptors(registry);
    }
	
	/**
     * 配置默认错误页面（仅用于内嵌tomcat启动时）
     * 错误code拦截，由于使用Spring-Security，403错误无法拦截，如果自定义异常，也不行(登录页都进不去)
     * 使用这种方式，在打包为war后不起作用
     *
     * @return
     */  
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
        return container -> {
            ErrorPage error403Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error/403");
            container.addErrorPages(error403Page);
        };
	}
}