package com.wuxi.service;

import java.io.IOException;

import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class RecourceTest {

	@Test
	public void fileTest() throws IOException{
		String filePath = "/Users/dasouche/Desktop/error.txt";
		Resource file = new FileSystemResource(filePath);
//		System.out.println(file.isReadable());
//		System.out.println(file.getFilename());
//		System.out.println(file.getDescription());
//		System.out.println(file.getURI());
//		System.out.println(file.getURL());
	}
}
