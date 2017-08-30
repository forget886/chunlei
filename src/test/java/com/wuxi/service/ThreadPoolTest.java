package com.wuxi.service;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ThreadPoolTest {

	@Test
	public void create(){
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 45, TimeUnit.SECONDS, new PriorityBlockingQueue<>(10));
		
	}
	
	/**
	 * 对volatile变量的单个读/写，看成是使用同一个监视器锁对这些单个读/写操作做了同步
	 */
	@Test
	public void valiteTest(){
		VolatileFeaturesExample example = new VolatileFeaturesExample();
		for(int i=0;i<10000;i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					example.getAndIncrement();
				}
			}).start();
		}
		System.out.println(example.get());
	}
}


class VolatileFeaturesExample {
    long vl = 0L;               // 64位的long型普通变量

    public synchronized void set(long l) {     //对单个的普通 变量的写用同一个监视器同步
        vl = l;
    }

    public void getAndIncrement () { //普通方法调用
        long temp = get();           //调用已同步的读方法
        temp += 1L;                  //普通写操作
        try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        set(temp);                   //调用已同步的写方法
    }
    public synchronized long get() { 
    //对单个的普通变量的读用同一个监视器同步
        return vl;
    }
}