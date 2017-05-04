package com.wuxi.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wuxi.bean.vo.User;

@Repository("userDao")
public class UserDao extends BaseDao{

	
//	public List<User> queryByName(String name){
//		List<User> userList = new ArrayList<>();
//		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from user where name = ?",new Object[]{name});
//		Iterator<Map<String, Object>> it = list.iterator();  
//		while(it.hasNext()) {  
//		   Map userMap = (Map) it.next();  
//		   User user = new User();
//		   user.setName(userMap.get("name").toString());
//		   userList.add(user);
//		}  
//		return userList;
//	}
	
	public List<User> queryByName(String name){  
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("name", name);
		return this.sqlSession.selectList("user.getUser", params);
	}
}
