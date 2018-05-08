package com.nriet.boot.rabbitmq.util;
/**
 * 消费者消息获取的callback接口，无需应答类
 * @author b_wangpei
 *
 */
public interface ConsumerHandlerInterfaces {
	public void handleMessage(Object rs); 
}
