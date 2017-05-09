package com.wuxi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class OperateService {
	
	private static final Logger logger = LoggerFactory.getLogger(OperateService.class);
	
	private  UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		OperateService operateService = (OperateService) context.getBean("operate");
		logger.info(operateService.getUserService().getUser("李强"));
	}

	public UserService getUserService() {
		return userService;
	}

}
