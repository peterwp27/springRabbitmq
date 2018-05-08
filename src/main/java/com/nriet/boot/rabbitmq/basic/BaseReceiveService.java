package com.nriet.boot.rabbitmq.basic;

import javax.annotation.PostConstruct;

import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nriet.boot.rabbitmq.config.ConsumerConfig;
import com.nriet.boot.rabbitmq.config.RabbitConfig;
import com.nriet.boot.rabbitmq.util.RabbitUtil;
/**
 * 发送服务基类，辅助完成producer的建立，供具体的service使用
 * @author b_wangpei
 *
 */
@Service
public abstract class BaseReceiveService {
	
	protected ConsumerConfig consumer;
	
	private String queueName;
	
	
	@Autowired
    private RabbitConfig rabbitConfig; 
	
	@PostConstruct  
    public void init() {
		RabbitUtil.rbConfig = this.rabbitConfig;
		this.consumer  = new ConsumerConfig(this.queueName); 
    }
	
	public BaseReceiveService(String queueName) {
		this.queueName = queueName;
	}
	/**
	 * 开启消息监听服务，自定下载消息体
	 */
	public void start(ChannelAwareMessageListener callback) {
		consumer.startListener(callback);
	}
	public abstract void start();
	/**
	 * 停止消息监听服务
	 */
	public void stop() {
		this.consumer.stopListener();
	}
}
