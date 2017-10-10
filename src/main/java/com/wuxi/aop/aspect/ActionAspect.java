package com.wuxi.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class ActionAspect {

	
	@After("within(com.wuxi.aop..*.*Impl+)")
	public void record(JoinPoint joinpoint){
		System.out.println("record");
		//业务类织入多个切面，按order决定织入顺序，生成一个代理类
		//System.out.println("代理类： " + joinpoint.getThis().getClass().getName());
		System.out.println("拦截方法： " + joinpoint.getSignature().getName());
	}
	
	@After("@target(com.wuxi.aop.annotation.Monitorable)")
	public void record2(JoinPoint joinpoint){
		System.out.println("record2");
		System.out.println("拦截方法： " + joinpoint.getSignature().getName());
	}
	
	@After("args(String,int) and args(name,age)")
	public void record3(JoinPoint joinpoint,String name,int age ){
		System.out.println("record3 " + name + age);
		System.out.println("拦截方法： " + joinpoint.getSignature().getName());
	}
	
	@AfterReturning(value="execution(* com.wuxi.aop.aspect.HelloWorldImpl.sell())",returning="name")
	public void record4(JoinPoint joinpoint,String name){
		System.out.println("record4 " + name);
		System.out.println("拦截方法： " + joinpoint.getSignature().getName());
	}
}
