package com.wuxi.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Service;

@Service("greetingInterceptor")
public class GreetingInterceptor implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object[] args = invocation.getArguments();
		
		System.out.println("环绕前 " + args[0]);
		Object object = invocation.proceed();
		System.out.println("环绕后 " + object);
		
		return object;
	}

}
