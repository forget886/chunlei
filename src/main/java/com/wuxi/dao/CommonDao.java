package com.wuxi.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("commonDao")
public class CommonDao extends BaseDao{

	 public  List<Map<String, Object>> getTableColumns(String table) {
        Map<String, String> obj = new HashMap<String, String>();
        obj.put("table", table);
        
        return sqlSession.selectList("common.TABLE_SCHEMA", obj);
     }
	 
	 public Map<String, Object> getObj(Map<String, Object> params){
		 return sqlSession.selectOne("common.select",params);
	 }
}