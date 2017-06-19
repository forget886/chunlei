package com.wuxi.service;

import org.junit.Test;

public class ProtectedTest {

	@Test
	public void pp(){
		Child child = new Child();
		child.p1();
	}
}


class Father{
	protected void p1(){
		System.out.println(1);
	}
}

class Child extends Father{
	public void p2(){
		System.out.println(2);
	}
}