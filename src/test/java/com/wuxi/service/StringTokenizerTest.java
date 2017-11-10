package com.wuxi.service;

import java.util.StringTokenizer;

import org.junit.Test;

public class StringTokenizerTest {

	@Test
	public void split(){
		StringTokenizer tokenizer = new StringTokenizer("this;is,a;o", ",;");
		while(tokenizer.hasMoreElements()){
			System.out.println(tokenizer.nextToken());
		}
	}
}
