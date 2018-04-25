package com.wuxi.service;

public interface HttpRequest {

	/**
	 * 
	 * @param qps 请求次数
	 * @param url 请求地址
	 * @param time 限时（秒）
	 * @param path 输出路径
	 */
	public void send(int qps,String url,int time,String path);
}
