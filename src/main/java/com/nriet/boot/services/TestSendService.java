package com.nriet.boot.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nriet.boot.rabbitmq.basic.BaseSendService;
import com.nriet.framework.util.PropertiesUtil;

/**
 * 针对每个队列建立一个发送服务
 * 父类中包含一个producer对象，可以直接在service中使用进行消息发送
 * 
 * @author b_wangpei
 */
@Service
public class TestSendService extends BaseSendService {
	private final static Logger logger = LoggerFactory.getLogger(TestSendService.class);
	private static PropertiesUtil propertis = PropertiesUtil.Instances("properties/config.properties");
	
	public TestSendService() {
		super(propertis.get("rabbitmq.queues1"));
	}
	
	public void send(Object o) {
		try {
			producer.send(o);
		}catch(Exception e) {
			logger.error("消息发送失败", e);
			
			//TODO  发送信息存库
			//dbMapper.insert(o);
			
		}
		
	}

}
