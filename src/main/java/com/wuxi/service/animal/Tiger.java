package com.wuxi.service.animal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tiger implements Animal{

	private static final Logger logger = LoggerFactory.getLogger(Animal.class);
	
	@Override
	public void eat() {
		logger.info("吃肉");
	}

}
