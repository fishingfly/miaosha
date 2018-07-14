package com.zy.miaosha.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.miaosha.redis.RedisService;

@Service
public class MQSender {
    
    private static Logger log = LoggerFactory.getLogger(MQSender.class);
    
    @Autowired
    private AmqpTemplate amqpTemplate;
    
    public void send(Object message) {
        log.info("MQ send message:" + message);
        String msg = RedisService.beanToString(message);
        amqpTemplate.convertAndSend(MQConfig.QUEUE, msg);
    }
}
