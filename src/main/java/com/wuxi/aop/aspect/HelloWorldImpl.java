package com.wuxi.aop.aspect;

import org.springframework.aop.framework.AopContext;

import com.wuxi.aop.annotation.Monitorable;

@Monitorable
public class HelloWorldImpl implements HelloWorld{

	@Override
	public void hello() {
		System.out.println("hello world!");
		//spring aop不拦截对象内部调用的方法
		//https://note.youdao.com/web/#/file/XL2UO9H/note/WEB16df64fbe984883fd5e70163fb34614a/
		//https://note.youdao.com/web/#/file/XL2UO9H/note/WEB654cd359feb73a5a6e7dcc87b3e985c6/
		if(AopContext.currentProxy() != null){
			((HelloWorld)AopContext.currentProxy()).sell();
		}else{
			this.sell();
		}
		
	}

	@Override
	public void bye(String name,int age) {
		System.out.println("good bye! " + name + " " + age);
	}

	@Override
	public String sell() {
		System.out.println("sell");
		return "aa";
	}

}
