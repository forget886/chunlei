package com.wuxi.service.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;

public class CompletionTest {

	@Test
	public void run(){
		CompletionService<Integer> completionService = new ExecutorCompletionService<>(Executors.newFixedThreadPool(3));
		for(int i = 0; i<10; i++){
			completionService.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					int ss = RandomUtils.nextInt(10);
					System.out.println(ss+"--");
					Thread.sleep(ss*1000);
					
					return ss;
				}
			});
		}
		for(int i=0; i<10; i++){
			try {
				Future<Integer> future = completionService.poll(1,TimeUnit.SECONDS);
				if(future != null){
					System.out.println(future.get());
				}else{
					System.out.println("none");
				}
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}


