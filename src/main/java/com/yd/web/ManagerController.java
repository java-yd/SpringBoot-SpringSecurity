package com.yd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yd.entity.YdProterties;

@EnableConfigurationProperties(YdProterties.class)
@RequestMapping("manager")
@Controller
public class ManagerController {

	@Autowired
	private  YdProterties  proterties;
	
	@ResponseBody
	@RequestMapping("getYdProperties")
	public  YdProterties   getYdProperties(){
		return  proterties;
	}
	
	/**
	 * 跳到登录页面
	 * @return
	 */
	@RequestMapping("toLogin")
	public  String   toLogin(){
		System.out.println("tologin");
		return  "login";
	}
	
	/**
	 * 该方法无用
	 * @param username
	 * @param password
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping("login")
//	public  String   login(String  username, String  password){
//		System.out.println(username);
//		return  username+"--success--"+password;
//	}
	
	/**
	 * 错误页面
	 * @return
	 */
	@RequestMapping("error")
	public  String   error(){
		System.out.println("error");
		return  "error";
	}
	
	/**
	 * 首页不拦截
	 * @return
	 */
	@RequestMapping("index")
	public  String  index(){
		System.out.println("index");
		return "index";
	}
	
	/**
	 * 跳转到添加管理员页面，必须登录
	 * @return
	 */
	@RequestMapping("addManager")
	public  String   addManager(){
		System.out.println("addManager");
		return  "addManager";
	}
	
	/**
	 * 重定向到首页
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("saveManager")
	public  String   saveManager(String  username,String  password){
		System.out.println(username +"--saveManager--"+password);
//		return  "redirect:index";   //跳到当前controller下的index接口
		return  "redirect:/manager/index";
	}
	
}
