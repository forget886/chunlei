package com.wuxi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service("operate")
public class OperateService {
	
	private static final Logger logger = LoggerFactory.getLogger(OperateService.class);
	
	@Autowired
	private  UserService userService;

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		OperateService operateService = (OperateService) context.getBean("operate");
		logger.info(operateService.getUserService().getUser("习近平"));
	}

	public UserService getUserService() {
		return userService;
	}

}
