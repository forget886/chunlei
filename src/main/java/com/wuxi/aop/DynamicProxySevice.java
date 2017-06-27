package com.wuxi.aop;

import java.lang.reflect.Proxy;

public class DynamicProxySevice {

	public static void main(String[] args) {
		System.out.println("jdk 代理...");
		jdkProxy();
		System.out.println("\n\ncglib 代理...");
		cglibProxy();
	}
	
	public static void jdkProxy(){
		Performance target = new PerformanceImpl();
		//将业务代码和横切代码编织到一起
		PerformHandler handler = new PerformHandler(target);
		//创建代理实例
		Performance proxy = (Performance) Proxy.newProxyInstance(
				target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), 
				handler);
		proxy.add(12);
		proxy.remove(20);
	}
	
	
	public static void cglibProxy(){
		CGlibProxy proxy = new CGlibProxy();
		//通过动态生成子类的方式创建代理类
		PerformanceImpl performance = (PerformanceImpl) proxy.getProxy(PerformanceImpl.class);
		
		performance.add(30);
		performance.remove(50);
	}
}
