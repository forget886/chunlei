package com.wuxi.aop.aspect;

public class HelloWorldImpl implements HelloWorld{

	@Override
	public  void hello() {
		System.out.println("hello world!");
	}

	@Override
	public void bye(String name) {
		System.out.println("good bye! " + name);
		
	}

}
