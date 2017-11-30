package com.wuxi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CircularReferB {
	
	/**
	 * 
	 * singletonFactories:
	 * {circularReferB=org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory$2@aa5455e,
	 *	circularReferA=org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory$2@75201592}
	 *	
	 * earlySingletonObjects:
	 * {circularReferA=com.wuxi.service.CircularReferA@34323f43}
	 *	
	 * result:
	 *  circularReferB 即将初始化,time now is 2017-11-27 04:52:27:664
	 *  circularReferB 已经初始化,time now is 2017-11-27 04:52:27:676
	 *  circularReferA 即将初始化,time now is 2017-11-27 04:52:27:677
	 *  circularReferA 已经初始化,time now is 2017-11-27 04:52:27:677
	 */
	
	@Autowired
	CircularReferA circularReferA;
}
