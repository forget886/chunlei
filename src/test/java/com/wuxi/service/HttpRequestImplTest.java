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

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
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
		int time = 1;
		String path = "/Users/zhanghui/Desktop/request.txt";
		send(qps, url, time, path);
	}
	
	

	@Override
	public void send(int qps, final String url, int time, String path) {
		if (qps <= 0 || StringUtil.isEmpty(url) || time <= 0 || StringUtil.isEmpty(path)) {
			return;
		}
		final String logFormat = "%s 耗时：%d ms";
		
		HttpParams params = new BasicHttpParams();
		params.setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 300);
		params.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 200);
		params.setBooleanParameter(CoreConnectionPNames.STALE_CONNECTION_CHECK, true);
		params.setLongParameter(ClientPNames.CONN_MANAGER_TIMEOUT, 600);
		PoolingClientConnectionManager connectionManager = new PoolingClientConnectionManager();
		connectionManager.setMaxTotal(2000);
		//同一个请求地址并发数
		connectionManager.setDefaultMaxPerRoute(2000);
		HttpClient client = new DefaultHttpClient(connectionManager, params);
		
		CompletionService<String> completionService = new ExecutorCompletionService<>(Executors.newFixedThreadPool(2000));
		for(int i = 0; i<qps; i++){
			completionService.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSS");
					long start = System.currentTimeMillis();
					request(url,client);
					return String.format(logFormat, format.format(new Date()),(System.currentTimeMillis() - start));
				}
			});
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSS");
		File file = new File(path);
        BufferedWriter writer = null;
        long deadline = System.currentTimeMillis() + 1000*time;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			for(int i=0; i<qps; i++){
				if(System.currentTimeMillis() > deadline) {
					System.out.println("结束:"+i);
					break;
				}
				try {
					Future<String> future = completionService.take();
					if(future != null){
				        try {
			                writer.write(future.get());
			                writer.newLine();
			                //System.out.println(i);
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
	
	private void request(final String url,HttpClient client) {
		HttpGet request = new HttpGet(url);
		try {
			HttpResponse  response = client.execute(request);
		} catch (Exception e) {
			LOG.error("",e);
		} finally {
            if (request != null)
            	request.releaseConnection();
        }
	}
	
}
