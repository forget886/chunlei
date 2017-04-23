package com.wuxi.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDao {

//	@Autowired
//	protected JdbcTemplate jdbcTemplate;
	
	@Autowired
	protected SqlSessionTemplate sqlSession;
}
