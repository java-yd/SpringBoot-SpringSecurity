package com.yd.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 由于Spring-Security把403无权限做处理了，但是为了返回json数据，所以做code拦截
 * 否则会返回Spring-Security的错误页面
 * 
 * @author FelixChen
 *
 */
@Controller
public class ErrorController {

	@ResponseBody
	@RequestMapping("/error/{code}")
	public Map<String, Object> error(@PathVariable Integer code) {
		Map<String, Object> map = new HashMap<>();
		map.put("code", 403);
		map.put("message", "暂无权限");
		return map;
	}

}
