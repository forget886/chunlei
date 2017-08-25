package com.wuxi.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

//@Aspect仅仅表示切面，spring环境要配置<aop:aspectj-autoproxy/>，还要创建切面bean
@Aspect
public class TimeHandler {
	@Before("execution(* com.wuxi.aop.aspect.HelloWorld.*(..))")
	public void print(){
		System.out.println("当前时间: " + System.currentTimeMillis());
	}
}

