package com.wuxi.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {

	public static void main(String[] args) {
		BoundedBuffer buffer = new BoundedBuffer();
		ExecutorService executor = Executors.newFixedThreadPool(10);
		executor.execute(new ReadThread("读1",buffer));
		executor.execute(new ReadThread("读2",buffer));
		executor.execute(new ReadThread("读3",buffer));
		executor.execute(new ReadThread("读4",buffer));
		executor.execute(new ReadThread("读5",buffer));
		executor.execute(new WriteThread("写1",buffer));
		executor.execute(new WriteThread("写2",buffer));
		executor.execute(new WriteThread("写3",buffer));
		executor.execute(new WriteThread("写4",buffer));
		executor.execute(new WriteThread("写5",buffer));
		
	}
}

class ReadThread extends Thread{
	BoundedBuffer buffer;
	String name;
	
	public ReadThread(String name,BoundedBuffer buffer){
		this.buffer = buffer;
		this.name = name;
	}
	
	public void run(){
		try {
			buffer.take(name);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


class WriteThread extends Thread{
	BoundedBuffer buffer;
	String name;
	public WriteThread(String name,BoundedBuffer buffer){
		this.buffer = buffer;
		this.name = name;
	}
	
	public void run(){
		try {
			buffer.put(name);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
	  
	   final Object[] items = new Object[20];//缓存队列  
	   //int putptr/*写索引*/, takeptr/*读索引*/, 
	   static int count/*队列中存在的数据个数*/;  
	  
	   public void put(String name) throws InterruptedException {  
	     lock.lock(); //此时变量x保存下来
	     System.out.println(name+ " 抢到锁");
	     try {  
	       while (count == items.length){//如果队列满了   
	    	   System.out.println(name+ " 阻塞");
	    	   notFull.await();//阻塞写线程,自动释放锁  唤醒之后且拿到锁将在此处继续往下执行 x为之前保存的
	    	   System.out.println(name+ " 阻塞 唤醒");
	       }
	       if(count == items.length){
	    	   return;
	       }
	       items[count++] = count-1;//赋值   
	       notEmpty.signal();//唤醒读线程  
	     } finally {  
	       System.out.println(name+ " 写入 count= " + (count-1));
	       System.out.println(name + " 释放锁");
	       lock.unlock();  
	     }  
	   }  
	  
	   public Object take(String name) throws InterruptedException {  
	     lock.lock();  
	     System.out.println(name+ " 抢到锁");
	     try {  
	       while (count == 0){//如果队列为空  
	    	   System.out.println(name+ " 阻塞");
	    	   notEmpty.await();//阻塞读线程,自动释放锁  唤醒之后且拿到锁将在此处继续往下执行
	    	   System.out.println(name+ " 阻塞 唤醒");
	    	   
	       }
	       if (count == 0) {
	    	   return null;
	       }
	       Object x = items[--count];//取值   
	       notFull.signal();//唤醒写线程  
	       System.out.println(name+ " 读出 count= " + (count) + " x= " + x);
	       return x;  
	     } finally {  
	       System.out.println(name + " 释放锁");
	       lock.unlock();  
	     }  
	   }   
	 }  
