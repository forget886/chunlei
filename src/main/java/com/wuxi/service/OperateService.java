package com.wuxi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wuxi.property.Factory;


public class OperateService {
	
	private static final Logger logger = LoggerFactory.getLogger(OperateService.class);
	
	private  UserService userService;
	
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		AbstractApplicationContext father = new ClassPathXmlApplicationContext("applicationContext.xml");
		//父子容器 子容器要刷新才能获取bean
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(father);
		context.refresh();
		Factory factory = (Factory) context.getBean("factory");
		logger.info(factory.getArea().getLocation());
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
//		Car car = context.getBean(Car.class);
//		System.out.println(car);
		context.destroy();
		father.destroy();
	}
	

	public UserService getUserService() {
		return userService;
	}

}
