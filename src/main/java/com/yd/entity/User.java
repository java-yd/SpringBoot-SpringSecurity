package com.yd.entity;

import com.yd.config.securityConfig.MyUserDetailsService;
import com.yd.config.securityConfig.UserInfo;

import lombok.Data;

public class User extends UserInfo{
	
	 	private Integer id;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

	   
}