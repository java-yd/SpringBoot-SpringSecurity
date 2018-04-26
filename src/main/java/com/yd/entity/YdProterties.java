package com.yd.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
@Data
@ConfigurationProperties(prefix="yd.test")
public class YdProterties {

	private  String  username;
	private  String  password;
	
	
}
