package com.wuxi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
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
		List<User> list = jdbcTemplate.query("select * from user where name = ?",new Object[]{name},new RowMapper<User>(){
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setName(rs.getString("name"));
				user.setAge(rs.getInt("age"));
				user.setSchool(rs.getString("school"));
				user.setBorthTime(rs.getTimestamp("borth_time"));
				user.setWorkDay(rs.getDate("work_day"));
				user.setDateCreate(rs.getTimestamp("date_create"));
				return user;
			}
			
		});
		
		
		return list;
	}
}
