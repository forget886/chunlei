package com.wuxi.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class OutofmemoryTest {

	static List<String> list = null;
	
	private int  leak = 1;
	
	public void stackLeak(){
		leak ++;
		stackLeak();
	}
	
	public static void main(String[] args) {
		/**
		 * -Xms20M -Xmx20M -Xmn10M  -XX:+PrintGC -XX:+PrintGCDetails -XX:-UseGCOverheadLimit
			-XX:+PrintGCDateStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Users/dasouche/Desktop/java.hprof
			-Xloggc:gc.log
		 * @param args
		 */
		//堆溢出
//		List<OutofmemoryTest> list = new ArrayList<OutofmemoryTest>();
//		while(true){
//			list.add(new OutofmemoryTest());
//		}
		//jdk1.8 静态变量、常量池都放到堆里
		//静态 溢出 会dump heap
		list = new ArrayList<>();
		int i = 0;
		try {
			while(true){
				list.add(String.valueOf(i++));
			}
		} catch (OutOfMemoryError e) {
			e.printStackTrace(System.err);
		}
		
		//常量溢出 会dump heap
//		List<String> list2 = new ArrayList<>();
//		int j = 0;
//		while(true){
//			list2 .add(String.valueOf(j++).intern());
//		}
		
		//-Xss256k 
		//栈溢出 不会dump heap
//		OutofmemoryTest oom = new OutofmemoryTest();
//		oom.stackLeak();
		
		//Dumping heap to java_pid40208.hprof ...
		//Heap dump file created [24124708 bytes in 0.160 secs]
		
		//-XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M
	}
	
	@Test
	public void internTest(){
		//java特殊，常量池早就有该引用
		String str = new StringBuilder("ja").append("va").toString();
		System.out.println(str.intern() == str);
		//jdk1.7及以后 常量池记录并返回首次出现的实例的引用
		String str2 = new StringBuilder("jjjj").append("va").toString();
		System.out.println(str2.intern() == str2);
	}
	
}
