package com.yd.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yd.entity.YdProterties;
import com.yd.service.RedisService;

@Controller
@RequestMapping()
public class IndexController {

	@Autowired
	private   YdProterties  proterties;
	@Autowired
	private  RedisService   redisService;
	
	@ResponseBody
	@RequestMapping("getYd")
	public  YdProterties   getYd(){
		return  proterties;
	}
	@ResponseBody
	@RequestMapping("testGet")
	public  String   testGet(){
		redisService.hSet("user", "age","hSet");
		return  (String) redisService.hGet("zhangsan","张三");
	}
	
}
