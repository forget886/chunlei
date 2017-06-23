package com.wuxi.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class MailSend implements ApplicationContextAware{
	
	private static final Logger logger = LoggerFactory.getLogger(MailSend.class); 
	
	private ApplicationContext ctx;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.ctx = applicationContext;
	}

	public void sendMail(String to){
		logger.info("模拟发送邮件：{}",to);
		MailSendEvent mse = new MailSendEvent(ctx, to);
		//向容器所有时间监听器发送事件
		ctx.publishEvent(mse);
	}
	
}
