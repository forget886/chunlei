package com.wuxi.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes.Name;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wuxi.util.JsonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class CommDaoTest {

	@Autowired
	private CommonDao commonDao;
	
	@Test
	public void getTableConlunm(){
		 List<Map<String, Object>> list = commonDao.getTableColumns("user");
		 System.out.println(JsonUtil.toJson(list));
	}
	
	@Test
	public void getUser(){
		Map<String, Object> params = new HashMap<>();
		//$ # 混用 而且会预编译处理
		String sql = "select * from user where name = #{name}";
		params.put("sql", sql);
		params.put("name", "李强");
		
		Map<String, Object> result = commonDao.getObj(params);
		System.out.println(result);
	}
}
