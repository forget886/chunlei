package com.wuxi.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class UserTest {

    private static final Logger LOG = LoggerFactory.getLogger(UserTest.class);


//	@Autowired
//	private  UserService userService;

    @Test
    public void getUser() {
//		userService.getUser("user");

        LOG.info("123");
    }
}
