package com.wuxi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service("operate")
public class OperateService {
	
	@Autowired
	private  UserService userService;

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		OperateService operateService = (OperateService) context.getBean("operate");
		operateService.getUserService().getUser("user");
	}

	public UserService getUserService() {
		return userService;
	}

}
