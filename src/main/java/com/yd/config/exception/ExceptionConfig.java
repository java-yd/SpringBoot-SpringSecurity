package com.yd.config.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建全局异常处理类：但是使用spring security的403无法拦截
 * 通过使用@ControllerAdvice定义统一的异常处理类，而不是在每个Controller中逐个定义。
 * @ExceptionHandler用来定义函数针对的异常类型
 */
@ControllerAdvice
public class ExceptionConfig {

	/**
	 * 返回json，跳转到页面也可以  用ModelAndView
	 * @param req
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Object defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		Map<String,Object> mav = new HashMap<>();
		mav.put("exception", e.getMessage());
		mav.put("url", req.getRequestURL());
		return mav;
	}
	
	/**
	 * 会输出runtime异常的信息
	 * @param req
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@ExceptionHandler(value = CommonException.class)
	public Object myErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		Map<String,String>  map = new  HashMap<>();
		map.put("code", "500");
		map.put("message", e.getMessage());
		return map;
	}
}
