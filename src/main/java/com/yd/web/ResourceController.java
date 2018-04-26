package com.yd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yd.entity.Resource;
import com.yd.service.ResourceService;

@Controller
@RequestMapping("resource")
public class ResourceController {

	@Autowired
	private  ResourceService   resourceService;
	
	@ResponseBody
	@RequestMapping("saveResource")
	public  String   saveResource(Resource  resource){
		resourceService.addResource(resource);
		return  "success";
	}
}
