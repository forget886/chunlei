package com.wuxi.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class MailSendListener implements ApplicationListener<MailSendEvent>{

	private static final Logger logger = LoggerFactory.getLogger(MailSendListener.class);
	
	/**
	 * 仅对MailSendEvent事件进行处理 instanceof
	 */
	@Override
	public void onApplicationEvent(MailSendEvent event) {
		logger.info("监听到向{}发送邮件",event.getTo());
		logger.info(event.getSource().toString());
	}

}
