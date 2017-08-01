package com.wuxi.service;

import org.junit.Test;

public class ProtectedTest {

	@Test
	public void pp(){
		Father child = new Child();
		child.p1();
		
		int a = 0b110110;//二进制
		int b = 110_110;
		System.out.println(a);
	}
}


class Father{
	protected static void p1(){
		System.out.println(1);
	}
}

class Child extends Father{
	
	protected static void p1(){
		System.out.println(11);
	}
	
	public void p2(){
		System.out.println(2);
	}
}