package com.wuxi.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;

import org.junit.Test;

public class PropertyTest {

	@Test
	public void load(){
		Properties properties = new Properties();
		InputStream inStream = PropertyTest.class.getClassLoader().getResourceAsStream("spring.handlers");
		try {
			properties.load(inStream);
			for(Entry<Object, Object> entry:properties.entrySet()){
				System.out.println(entry.getKey());
				System.out.println(entry.getValue());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
