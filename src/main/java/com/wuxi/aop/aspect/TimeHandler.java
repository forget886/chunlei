package com.wuxi.aop.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Aspect仅仅表示切面，spring环境要配置<aop:aspectj-autoproxy/>，还要创建切面bean
@Aspect
public class TimeHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(TimeHandler.class);
	 
	@Before("execution(* com.wuxi.aop.aspect.HelloWorld.h*(..))")
	public void printH(JoinPoint joinpoint){
		System.out.println(joinpoint.toLongString());
		System.out.println("拦截 hello " + System.currentTimeMillis());
	}
	
	@Before("execution(* com.wuxi.aop.aspect.HelloWorld.b*(..)) and args(name)")
	public void printB(JoinPoint joinpoint,String name){
		System.out.println(name);
		System.out.println(joinpoint.getKind());
		System.out.println(joinpoint.getTarget().getClass().getName());
		System.out.println(joinpoint.getThis().getClass().getName());
		System.out.println(joinpoint.getClass().getName());
		System.out.println(joinpoint.getSignature().getName());
		System.out.println(joinpoint.getStaticPart().toLongString());
		
		MethodSignature signature =  (MethodSignature) joinpoint.getSignature();
		Method method = signature.getMethod();
		System.out.println("拦截 bye " + System.currentTimeMillis());
	}
}

