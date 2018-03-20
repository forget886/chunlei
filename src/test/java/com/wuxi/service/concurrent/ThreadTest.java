package com.wuxi.service.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ThreadTest {

	@Test
	public void create(){
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 45, TimeUnit.SECONDS, new PriorityBlockingQueue<>(10));
		
	}
	
	
	@Test
	public void yieldTest(){
		Producer producer = new Producer();
		Consumer consumer = new Consumer();
		producer.setPriority(Thread.MIN_PRIORITY);
		consumer.setPriority(Thread.MAX_PRIORITY);
		producer.start();
		consumer.start();
	}
	
	
	class Producer extends Thread{
		
		public void run(){
			for(int i=0; i<5; i++){
				System.out.println("condruce " + i);
				//cpu让给别人
				Thread.yield();
			}
		}
	}

	class Consumer extends Thread{
		
		public void run(){
			for(int i=0; i<5; i++){
				System.out.println("comsume " + i);
				//cpu让给别人
				Thread.yield();
			}
		}
	}
	
	@Test
	public void joinTest(){
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("t1 start..");
				System.out.println("t1 sleep 2s...");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();
		try {
			//t1执行完才能继续执行主线程
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("主线程执行...");
	}

	static volatile int i = 0 ;
	
	public  static void increase(){
		synchronized(ThreadTest.class){
			i++;
		}
	}
	
	/**
	 * 对volatile变量的单个读/写，看成是使用同一个监视器锁对这些单个读/写操作做了同步
	 */
	@Test
	public void valiteTest(){
		//VolatileFeaturesExample example = new VolatileFeaturesExample();
		int count = 20;
		final CountDownLatch latch = new CountDownLatch(count);
		for(int i=0; i<count; i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					//example.getAndIncrement();
						for(int j=0; j<100000; j++){
							increase();
						}
						latch.countDown();
					}
				
			}).start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(i);
		//System.out.println(example.get());
		
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