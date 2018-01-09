package com.wuxi.service.animal;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.InitializingBean;

public class Pig implements Animal,InitializingBean{

	private static final Logger logger = LoggerFactory.getLogger(Animal.class);
	/**
	 *  pig construct
		2017-12-27 22:38:39 [main][com.wuxi.service.TimeRecoder-25] INFO  - com.wuxi.service.animal.Pig#0 即将初始化,time now is 2017-12-27 10:38:39:550
		postContruct
		afterpropertiesSet
		init
		2017-12-27 22:38:39 [main][com.wuxi.service.TimeRecoder-32] INFO  - com.wuxi.service.animal.Pig#0 已经初始化,time now is 2017-12-27 10:38:39:552
	 */
	public  Pig() {
		System.out.println("pig construct");
	}
	
	@Override
	public void eat() {
		logger.info("吃草");
	}
	
	public void init(){
		System.out.println("init");
	}
	
	@PostConstruct
	public void initt(){
		System.out.println("postContruct");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterpropertiesSet");
	}

}
