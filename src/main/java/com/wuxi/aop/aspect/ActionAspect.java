package com.wuxi.aop.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class ActionAspect {

	@After("within(com.wuxi.aop..*.*Impl+)")
	public void record(){
		System.out.println("record");
	}
	
	@After("@target(com.wuxi.aop.annotation.Monitorable)")
	public void record2(){
		System.out.println("record2");
	}
	
	@After("args(String) and args(name)")
	public void record3(String name){
		System.out.println("record3 " + name);
	}
}
