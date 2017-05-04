package com.wuxi.service;


import java.sql.Time;

import org.junit.Test;

public class CommonTest {

	@Test
	public void dateTest(){
		System.out.println(new Time(System.currentTimeMillis()));
	}
}
