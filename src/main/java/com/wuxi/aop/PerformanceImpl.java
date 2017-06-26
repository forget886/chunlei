package com.wuxi.aop;

public class PerformanceImpl implements Performance{

	@Override
	public void add(int id) {
		System.out.println("增加id：" + id);
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remove(int id) {
		System.out.println("删除id： " + id);
		try {
			Thread.sleep(25);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
