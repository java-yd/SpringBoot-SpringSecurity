package com.yd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.entity.Resource;
import com.yd.mapper.ResourceMapper;
import com.yd.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private  ResourceMapper   resourceMapper;

	@Override
	public int addResource(Resource resource) {
		return resourceMapper.insert(resource);
	}
	
	
}
