package com.wuxi.aop;

import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

public class TestBeforeAdvice {

	public static void main(String[] args) {
		Waiter waiter = new WaiterImpl();
		BeforeAdvice advice = new GreetingBeforeAdvice();
		
		ProxyFactory pf = new ProxyFactory();
		
		pf.setTarget(waiter);
		//指定对接口代理(jdk)，否则对类代理(cglib)
		pf.setInterfaces(waiter.getClass().getInterfaces());
		//优化代理
		pf.setOptimize(true);
		//可设置多个增强
		pf.addAdvice(advice);
	
		Waiter proxy = (Waiter) pf.getProxy();
		proxy.greetTo("xixi");
		proxy.serveTo("wang");
	}
}
