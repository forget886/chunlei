package com.wuxi.objectproxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;



public class CGlibProxy implements MethodInterceptor{
	
	private Object target;
	
	public CGlibProxy(Object target){
		this.target = target;
	}
	
	/**
	 * 拦截父类所有方法
	 */
	@Override
	public Object intercept(Object proxyObj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		System.out.println(proxyObj.getClass().getName() + "." + methodProxy.getSuperName()+"|"+method.getName());
		long start = System.currentTimeMillis();
		
		Object result = methodProxy.invoke(target, args);
		result = method.invoke(target, args);
		//通过代理类调用父类的方法
		result = methodProxy.invokeSuper(proxyObj, args);
		System.out.println("耗时：" + (System.currentTimeMillis() - start));
		return result;
	}

}
