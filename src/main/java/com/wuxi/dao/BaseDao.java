package com.wuxi.dao;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class BaseDao<T> {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    protected SqlSessionTemplate sqlSession;

    protected <T> List<T> selectList(String sql, Object params) {
        return sqlSession.selectList(sql, params);
    }

    protected <T> T selectOne(String sql, Object params) {
        return sqlSession.selectOne(sql, params);
    }
}
