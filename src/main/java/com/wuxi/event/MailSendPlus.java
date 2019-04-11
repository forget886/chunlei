package com.wuxi.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("mailsendplus")
public class MailSendPlus extends MailSend {

    private static final Logger logger = LoggerFactory.getLogger(MailSendPlus.class);

    public void sendMail(String to) {
        logger.info("模拟发送邮件：{}", to);
        MailSendEvent mse = new MailSendEvent(ctx, to);
        //向容器所有时间监听器发送事件
        ctx.publishEvent(mse);
    }
}


