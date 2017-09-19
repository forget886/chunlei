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
		System.out.println("连接点： " + joinpoint.getStaticPart().toLongString());
		System.out.println("切点拦截的目标方法签名： " + joinpoint.getSignature().getName());
		System.out.println("拦截 hello ");
	}
	
	@Before("execution(* com.wuxi.aop.aspect.HelloWorld.b*(..)) and args(name)")
	public void printB(JoinPoint joinpoint,String name){
		System.out.println("参数： " + name);
		System.out.println("连接点类型： " + joinpoint.getKind());
		System.out.println("切入的目标类： "+joinpoint.getTarget().getClass().getName());
		System.out.println("代理类： " + joinpoint.getThis().getClass().getName());
		System.out.println("切点类型： " + joinpoint.getClass().getName());
		System.out.println("连接点：" + joinpoint.getStaticPart().toLongString());
		System.out.println("切点拦截的目标方法签名： " + joinpoint.getSignature().getName());
		
		MethodSignature signature =  (MethodSignature) joinpoint.getSignature();
		Method method = signature.getMethod();
		System.out.println("拦截的方法：" + method.getName());
		System.out.println("拦截 bye ");
	}
}

