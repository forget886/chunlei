package com.wuxi.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class GreetingBeforeAdvice implements MethodBeforeAdvice{

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		String name = (String)args[0];
		System.out.println(method.getDeclaringClass().getSimpleName());
		System.out.println(method.getReturnType().getSimpleName());
		System.out.println("hello! " + name);
	}

}