package com.wuxi.aop;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;



public class CGlibProxy implements MethodInterceptor{
	
	
	/**
	 * 拦截父类所有方法
	 */
	@Override
	public Object intercept(Object proxyObj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		System.out.println(proxyObj.getClass().getName() + "." + methodProxy.getSuperName());
		long start = System.currentTimeMillis();
		//通过代理类调用父类的方法
		Object result = methodProxy.invokeSuper(proxyObj, args);
		System.out.println("耗时：" + (System.currentTimeMillis() - start));
		return result;
	}

}
