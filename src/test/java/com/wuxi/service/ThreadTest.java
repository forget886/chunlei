package com.wuxi.service;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {

	public static void main(String[] args) {
		final BoundedBuffer buffer = new BoundedBuffer();
		new Thread(new Runnable() {
			@Override
			public void run() {
				BoundedBuffer.count = 0;
				while(true){
					try {
						buffer.put(BoundedBuffer.count);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					try {
						buffer.take();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
	}
}


/**
 *  栈读写的实现
 * @author dasouche
 *
 */
class BoundedBuffer {  
	   final Lock lock = new ReentrantLock();//锁对象  
	   final Condition notFull  = lock.newCondition();//写线程条件   
	   final Condition notEmpty = lock.newCondition();//读线程条件   
	  
	   final Object[] items = new Object[1000];//缓存队列  
	   //int putptr/*写索引*/, takeptr/*读索引*/, 
	   static int count/*队列中存在的数据个数*/;  
	  
	   public void put(Object x) throws InterruptedException {  
	     lock.lock(); //此时变量x保存下来
	     System.out.println("写 抢到锁");
	     try {  
	       while (count == items.length){//如果队列满了   
	    	   System.out.println("写 阻塞");
	    	   notFull.await();//阻塞写线程,自动释放锁  唤醒之后且拿到锁将在此处继续往下执行 x为之前保存的
	    	   System.out.println("写 阻塞 唤醒");
	       }
	       if(count == items.length){
	    	   return;
	       }
	       items[count++] = x;//赋值   
	       notEmpty.signal();//唤醒读线程  
	     } finally {  
	       System.out.println("写入 count= " + (count-1) + " x= " + x);
	       lock.unlock();  
	     }  
	   }  
	  
	   public Object take() throws InterruptedException {  
	     lock.lock();  
	     System.out.println("读 抢到锁");
	     try {  
	       while (count == 0){//如果队列为空  
	    	   System.out.println("读 阻塞");
	    	   notEmpty.await();//阻塞读线程,自动释放锁  唤醒之后且拿到锁将在此处继续往下执行
	    	   System.out.println("读 阻塞 唤醒");
	    	   
	       }
	       if (count == 0) {
	    	   return null;
	       }
	       Object x = items[--count];//取值   
	       notFull.signal();//唤醒写线程  
	       System.out.println("读出 count= " + (count) + " x= " + x);
	       return x;  
	     } finally {  
	       lock.unlock();  
	     }  
	   }   
	 }  
