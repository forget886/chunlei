package com.wuxi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuxi.dao.UserDao;
import com.wuxi.service.animal.Animal;

@Service("userService")
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserDao userDao;
	
	private Animal animal;
	
	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public String getUser(String name){
		animal.eat();
		
		return userDao.queryByName(name).toString();
	}
}
