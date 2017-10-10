package com.wuxi.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

public class TimeRecoder implements BeanPostProcessor,Ordered{

	private static final Logger logger = LoggerFactory.getLogger(TimeRecoder.class);
	
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
	
 	@Override
	public int getOrder() {
		return 0;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		logger.info(beanName+" 即将初始化,time now is "+ DATE_FORMAT.format(new Date()));
		//如果返回null，则后面的后处理器（先后顺序由order决定，数字小的在前）将不会调用?
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		logger.info(beanName+" 已经初始化,time now is "+ DATE_FORMAT.format(new Date()));
		return bean;
	}

}
