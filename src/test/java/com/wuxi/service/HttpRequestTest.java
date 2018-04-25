package com.wuxi.service;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class HttpRequestTest {

	private static final Logger LOG = LoggerFactory.getLogger(HttpRequestTest.class);
	
	@Test
	public void send() {
		String url = "http://www.sina.com";
		int qps = 3000;
		CompletionService<Integer> completionService = new ExecutorCompletionService<>(Executors.newFixedThreadPool(500));
		for(int i = 0; i<qps; i++){
			completionService.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					long start = System.currentTimeMillis();
					request(url);
					return (int) ((System.currentTimeMillis() - start)/1000);
				}
			});
		}
		for(int i=0; i<qps; i++){
			try {
				Future<Integer> future = completionService.take();
				if(future != null){
					LOG.info("耗时：{}",future.get());
				}
			} catch (Exception e) {
				LOG.error("",e);
			} 
		}
	}
	
	public	void request( String url) {
		HttpClientParams params = new HttpClientParams();
        params.setConnectionManagerTimeout(5);
        params.setSoTimeout(5);

	    HttpClient client = new HttpClient(params);
		GetMethod getMethod = new GetMethod(url);
		try {
			int  code = client.executeMethod(getMethod);
			String response = getMethod.getResponseBodyAsString();
		} catch (Exception e) {
			//LOG.error("",e);
		} 
	}
	
}
