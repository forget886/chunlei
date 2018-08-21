package com.wuxi.aop;

import com.wuxi.aop.annotation.Monitorable;

public class WaiterImpl implements Waiter{

	public WaiterImpl() {
		System.out.println("waiter init...");
	}
	
	
	@Override
	public String greetTo(String name) {
		System.out.println("greet to " + name);
		//throw new RuntimeException("boom!!!");
		return name + "...";
	}

	@Override
	public void serveTo(String name) {
		System.out.println("serve to " + name);
	}


}
