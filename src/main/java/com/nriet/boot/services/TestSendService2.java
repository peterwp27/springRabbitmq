package com.nriet.boot.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nriet.boot.controller.PublicController;
import com.nriet.boot.rabbitmq.basic.BaseSendService;
import com.nriet.framework.util.PropertiesUtil;

/**
 * 针对每个队列建立一个发送服务
 * 
 * @author b_wangpei
 */
@Service("testService2")
public class TestSendService2 extends BaseSendService {
	private final static Logger logger = LoggerFactory.getLogger(TestSendService2.class);
	private static PropertiesUtil propertis = PropertiesUtil.Instances("properties/config.properties");
	
	public TestSendService2() {
		super(propertis.get("rabbitmq.queues2"));
	}

}
