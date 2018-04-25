package com.wuxi.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wuxi.util.StringUtil;

public class HttpRequestImplTest implements HttpRequest{

	private static final Logger LOG = LoggerFactory.getLogger(HttpRequestImplTest.class);
	
	@Test
	public void sendTest() {
		String url = "http://www.baidu.com";
		int qps = 3000;
		int time = 60;
		String path = "/Users/zhanghui/Desktop/request.txt";
		send(qps, url, time, path);
	}
	
	

	@Override
	public void send(int qps, String url, int time, String path) {
		if (qps <= 0 || StringUtil.isEmpty(url) || time <= 0 || StringUtil.isEmpty(path)) {
			return;
		}
		String logFormat = "%s 耗时：%d ms";
		
		long deadline = System.currentTimeMillis() + 1000*time;
	    
		CompletionService<String> completionService = new ExecutorCompletionService<>(Executors.newFixedThreadPool(2000));
		for(int i = 0; i<qps; i++){
			completionService.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSS");
					long start = System.currentTimeMillis();
					request(url);
					return String.format(logFormat, format.format(new Date()),(System.currentTimeMillis() - start));
				}
			});
		}
		File file = new File(path);
        BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			for(int i=0; i<qps; i++){
				if(System.currentTimeMillis() > deadline) {
					System.out.println(i);
					break;
				}
				try {
					Future<String> future = completionService.take();
					if(future != null){
				        try {
			                writer.write(future.get());
			                writer.newLine();
				        } catch (FileNotFoundException e) {
				            LOG.error("",e);
				        } catch (IOException e) {
				            LOG.error("",e);
				        }
					}
				} catch (Exception e) {
					LOG.error("",e);
				} 
			}
			
		} catch (IOException e1) {
			LOG.error("",e1);
		}finally {
			if(writer != null) {
				try {
					writer.flush();
				} catch (IOException e1) {
					LOG.error("",e1);
				}
				try {
					writer.close();
				} catch (IOException e) {
					LOG.error("",e);
				}
			}
			
		}
	}
	
	private void request(String url) {
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
		} finally {
            if (getMethod != null)
            	getMethod.releaseConnection();
        }
	}
	
}
