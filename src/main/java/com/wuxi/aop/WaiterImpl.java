package com.wuxi.aop;

public class WaiterImpl implements Waiter{

	public WaiterImpl() {
		System.out.println("waiter init...");
	}
	
	
	@Override
	public void greetTo(String name) {
		System.out.println("greet to " + name);
	}

	@Override
	public void serveTo(String name) {
		System.out.println("serve to " + name);
	}

}
