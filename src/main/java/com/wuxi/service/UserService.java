package com.wuxi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.wuxi.dao.BaseDao;
import com.wuxi.dao.UserDao;
import com.wuxi.dao.UserMapper;
import com.wuxi.service.animal.Animal;

@Service("userService")
public class UserService extends BaseDao implements DisposableBean,InitializingBean,ApplicationContextAware{

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserMapper userMapper;
	
	private Animal animal;
	
	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		System.out.println("set animal");
		this.animal = animal;
	}

//	public String getUser(String name){
//		animal.eat();
//		return userDao.queryByName(name).toString();
//	}
	
	public String getUser(String name){
		return userMapper.queryByName(name).toString();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		logger.info("context");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("init");
	}

	@Override
	public void destroy() throws Exception {
		logger.info("destroy");
	}

}
