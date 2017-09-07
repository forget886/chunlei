package com.wuxi.objectproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.wuxi.aop.WaiterImpl;

public class ObjectHandler implements InvocationHandler{

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("阿猫阿狗");
		//会报错 java.lang.IllegalArgumentException: object is not an instance of declaring class
		//		at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
		//		at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
		//		at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
		//		at java.lang.reflect.Method.invoke(Method.java:498)
		//		at com.wuxi.objectproxy.ObjectHandler.invoke(ObjectHandler.java:14)
		//		at com.sun.proxy.$Proxy0.add(Unknown Source)
		//		at com.wuxi.objectproxy.DynamicProxySevice.ObjectProxy(DynamicProxySevice.java:39)
		//		at com.wuxi.objectproxy.DynamicProxySevice.main(DynamicProxySevice.java:13)
		return method.invoke(new WaiterImpl(), args);
	}

}
