package com.wuxi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wuxi.bean.vo.Car;


public class OperateService {
	
	private static final Logger logger = LoggerFactory.getLogger(OperateService.class);
	
	private  UserService userService;
	
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//OperateService operateService = (OperateService) context.getBean("operate");
//		logger.info(operateService.getUserService().getUser("李强"));
//		ListableBeanFactory factory = context;
//		logger.info("bean count:{}",factory.getBeanDefinitionCount());
//		for(String name : factory.getBeanDefinitionNames()){
//			logger.info(name);
//		}
		//context只有getResources支持Ant风格的路径
		//logger.info(context.getResource("classpath:logba*.xml").exists()+"");
//		Resource[] resources = null;
//		try {
//			resources = context.getResources("classpath:logba*.xml");
//		} catch (IOException e) {
//		}
//		for(Resource r:resources){
//			logger.info(r.getDescription()+"");
//		}
		Car car = context.getBean(Car.class);
		System.out.println(car);
		context.close();
	}
	

	public UserService getUserService() {
		return userService;
	}

}
