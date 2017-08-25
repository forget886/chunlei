package com.wuxi.objectproxy;

import java.lang.reflect.Proxy;

import net.sf.cglib.proxy.Enhancer;

public class DynamicProxySevice {

	public static void main(String[] args) {
//		System.out.println("jdk动态代理...");
//		jdkProxy();
		System.out.println("\n\ncglib动态代理...");
		cglibProxy();
		System.out.println("over...");
	}
	
	public static void jdkProxy(){
		Performance target = new PerformanceImpl();
		//将业务代码和横切代码编织到一起
		PerformHandler handler = new PerformHandler(target);
		//生成的代理类保存到磁盘
	    //System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");  
		//创建代理实例
		Performance proxy = (Performance) Proxy.newProxyInstance(
				target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), 
				handler);
		//生成的代理类保存到磁盘
		//ProxyUtils.generateClassFile(target.getClass(), "PerformanceProxy");
		
		proxy.add(12);
		proxy.remove(20);
	}
	
	
	public static void cglibProxy(){
		CGlibProxy proxy = new CGlibProxy();
		//cglib 中加强器，用来创建动态代理 
		Enhancer enhancer = new Enhancer();
		//设置要创建动态代理的类  
		enhancer.setSuperclass(PerformanceImpl.class);
		//设置回调，这里相当于是对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实行intercept()方法进行拦截  
		enhancer.setCallback(proxy);
		//通过字节码技术动态生成子类实例
		PerformanceImpl performance = (PerformanceImpl) enhancer.create();
		
		//ProxyUtils.generateCglibClassFile(enhancer, PerformanceImpl.class.getResource(".").getPath(), performance.getClass().getSimpleName());
		
		performance.add(30);
		performance.remove(50);
	}
	
}
