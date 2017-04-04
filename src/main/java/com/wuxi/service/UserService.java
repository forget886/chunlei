package com.wuxi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	public String getUser(String name){
		logger.info("name:{}",name);
		return name;
	}
}
