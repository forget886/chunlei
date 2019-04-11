package com.wuxi.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.collections.functors.TruePredicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuxi.bean.vo.User;
import com.wuxi.dao.BaseDao;
import com.wuxi.dao.UserDao;
import com.wuxi.dao.UserMapper;
import com.wuxi.service.animal.Animal;

@Service("userService")
public class UserService extends BaseDao implements DisposableBean, InitializingBean, ApplicationContextAware {

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

    public String getUser(String name) {

        return userMapper.queryByName(name).toString();
    }

    public void updateUser(User user) {
        int a = userMapper.updateUser(user);
        System.out.println(a);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("context");
    }

    @PostConstruct
    public void init2() {
        logger.info("init 2");
    }

    @PreDestroy
    public void destory2() {
        logger.info("destory 2");
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
