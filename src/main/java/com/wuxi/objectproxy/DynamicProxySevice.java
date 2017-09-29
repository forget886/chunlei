package com.wuxi.objectproxy;

import java.lang.reflect.Proxy;

import net.sf.cglib.proxy.Enhancer;

public class DynamicProxySevice {

	public static void main(String[] args) {
		System.out.println("jdk动态代理...");
		jdkProxy();
		// System.out.println("随便代理");
		// ObjectProxy();
		// System.out.println("\n\ncglib动态代理...");
		// cglibProxy();
		// System.out.println("over...");
	}

	public static void ObjectProxy() {
		// 直接由接口生成代理对象
		Performance proxy = (Performance) Proxy.newProxyInstance(Performance.class.getClassLoader(),
				new Class[] { Performance.class }, new ObjectHandler());
		proxy.add(0);
		proxy.remove(0);
	}

	public static void jdkProxy() {
		Performance target = new PerformanceImpl();
		// 将业务代码和横切代码编织到一起
		PerformHandler handler = new PerformHandler(target);
		// 生成的代理类保存到磁盘
		// System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles",
		// "true");
		// 创建代理实例
		// JDK动态代理机制是委托机制，具体说动态实现接口类，在动态生成的实现类里面委托为hanlder去调用原始实现类方法。
		// 代理类与实现类没有继承或实现关系 所以不能强转
		Performance proxy = (Performance) handler.getProxy();
		// 生成的代理类保存到磁盘
		// ProxyUtils.generateClassFile(target.getClass(), "PerformanceProxy");

		proxy.add(12);
		proxy.remove(20);
	}

	public static void cglibProxy() {
		// System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,
		// "/Users/dasouche/Downloads");
		CGlibProxy proxy = new CGlibProxy();
		// cglib 中加强器，用来创建动态代理
		Enhancer enhancer = new Enhancer();
		// 设置要创建动态代理的类
		enhancer.setSuperclass(PerformanceImpl.class);
		// 设置回调，这里相当于是对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实行intercept()方法进行拦截
		enhancer.setCallback(proxy);
		// 通过字节码技术动态生成子类实例
		PerformanceImpl performance = (PerformanceImpl) enhancer.create();

		// ProxyUtils.generateCglibClassFile(enhancer,
		// PerformanceImpl.class.getResource(".").getPath(),
		// performance.getClass().getSimpleName());

		performance.add(30);
		performance.remove(50);
	}

}
