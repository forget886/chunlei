package com.wuxi.event;

import java.util.EventObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.stereotype.Service;

@Service
public class MailSendListener implements ApplicationListener<MailSendEvent>{

	private static final Logger logger = LoggerFactory.getLogger(MailSendListener.class);
	
	/**
	 * 仅对MailSendEvent事件进行处理 instanceof
	 */
	@Override
	public void onApplicationEvent(MailSendEvent event) {
		logger.info("向{}发送邮件",event.getTo());
		logger.info(event.getClass().isAssignableFrom(EventObject.class)+"");
		if(event instanceof ApplicationContextEvent){
			logger.info("true");
		}
		
	}

}
