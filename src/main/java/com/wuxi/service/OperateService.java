package com.wuxi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class OperateService {
	
	private static final Logger logger = LoggerFactory.getLogger(OperateService.class);
	
	private  UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//		OperateService operateService = (OperateService) context.getBean("operate");
//		logger.info(operateService.getUserService().getUser("李强"));
//		ListableBeanFactory factory = context;
//		logger.info("bean count:{}",factory.getBeanDefinitionCount());
//		for(String name : factory.getBeanDefinitionNames()){
//			logger.info(name);
//		}
		context.close();
	}
	

	public UserService getUserService() {
		return userService;
	}


}
