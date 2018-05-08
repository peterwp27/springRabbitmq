package com.nriet.boot.rabbitmq.config;

import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
/**
 * 
 * @author b_wangpei
 *
 */
public class ConsumerSimpleMessageListenerContainer extends SimpleMessageListenerContainer {
	 public void startConsumers() throws Exception {  
	        super.doStart();  
	    }  
}
