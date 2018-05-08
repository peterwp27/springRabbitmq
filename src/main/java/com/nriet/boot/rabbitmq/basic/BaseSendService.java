package com.nriet.boot.rabbitmq.basic;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nriet.boot.rabbitmq.config.ProducerConfiguration;
import com.nriet.boot.rabbitmq.config.RabbitConfig;
import com.nriet.boot.rabbitmq.util.RabbitUtil;
/**
 * 发送服务基类，辅助完成producer的建立，供具体的service使用
 * @author b_wangpei
 *
 */
@Service
public abstract class BaseSendService {
	
	private final static Logger logger = LoggerFactory.getLogger(BaseSendService.class);
	
	protected ProducerConfiguration producer;
	private String queueName;
	
	@Autowired
    private RabbitConfig rabbitConfig; 
	
	@PostConstruct  
    public void init() {
		RabbitUtil.rbConfig = this.rabbitConfig;
		this.producer = new ProducerConfiguration(this.queueName);
    }
	
	public BaseSendService(String queueName) {
		this.queueName = queueName;
	}
	
	public void send(Object o) {
		producer.send(o);
	}

}
