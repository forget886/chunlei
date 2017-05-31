package com.wuxi.service;

import java.io.IOException;

import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class RecourceTest {

	@Test
	public void fileTest() throws IOException{
//		String filePath = "/Users/dasouche/Desktop/error.txt";
//		Resource file = new FileSystemResource(filePath);
//		
//		System.out.println(file.isReadable());
		
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resolver.getResources("classpath*:logbac?.xml");
		for(Resource r:resources){
			System.out.println(r.getDescription());
		}
	}
}
