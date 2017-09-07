package com.wuxi.objectproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PerformHandler implements InvocationHandler{
	
	private Object target;
	
	public PerformHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		long start = System.currentTimeMillis();
		System.out.println("代理类：" + proxy.getClass().getName());
		System.out.println(target.getClass().getName() + "." + method.getName());
		//反射调用业务类的方法
		Object result = method.invoke(target, args);
		System.out.println("耗时：" + (System.currentTimeMillis() - start));
		return result;
	}

	
	public Object getProxy(){
		return Proxy.newProxyInstance(
				target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), 
				this);
	}
}


