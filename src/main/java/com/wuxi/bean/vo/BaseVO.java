package com.wuxi.bean.vo;

import java.sql.Timestamp;
import java.util.Date;

public class BaseVO {

	protected Date borthTime;
	private Date workDay;
	private Timestamp dateCreate;
	
	public Date getBorthTime() {
		return borthTime;
	}
	protected void setBorthTime(Date borthTime) {
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
}
