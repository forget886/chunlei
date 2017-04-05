package com.wuxi.bean.vo;

import java.sql.Timestamp;
import java.util.Date;

public class User {

	private int id;
	private String name;
	private int age;
	private String school;
	private Date borthTime;
	private Date workDay;
	private Timestamp dateCreate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public Date getBorthTime() {
		return borthTime;
	}
	public void setBorthTime(Date borthTime) {
		this.borthTime = borthTime;
	}
	public Date getWorkDay() {
		return workDay;
	}
	public void setWorkDay(Date workDay) {
		this.workDay = workDay;
	}
	public Timestamp getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(Timestamp dateCreate) {
		this.dateCreate = dateCreate;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", school=" + school + ", borthTime=" + borthTime
				+ ", workDay=" + workDay + ", dateCreate=" + dateCreate + "]";
	}
	
}
