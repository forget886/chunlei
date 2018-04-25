package com.wuxi.service;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

public class HttphttpTest {

	public static void main(String[] args) {
		String url = "http://www.baidu.com";
		int qps = 3000;
		ExecutorService executor = Executors.newFixedThreadPool(500);
		for(int i=0;i<qps;i++) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					long start = System.currentTimeMillis();
					//request(url);
					System.out.println("耗时："+(System.currentTimeMillis() - start)/1000+"秒");
				}
			});
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executor.shutdown();
		System.out.println("结束");
	}
	
	
	
	
	
}
