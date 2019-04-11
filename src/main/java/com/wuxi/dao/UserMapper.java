package com.wuxi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.wuxi.bean.vo.User;

public interface UserMapper {

    //@Select("select id,name,age,school,borth_time as borthTime,work_day as workDay,date_create as dateCreate from User where name = #{name}")
    public List<User> queryByName(String name);

    public int addUser(User user);

    public int updateUser(User user);

}
