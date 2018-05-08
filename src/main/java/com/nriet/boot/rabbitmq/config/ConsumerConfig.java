package com.nriet.boot.rabbitmq.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

import com.nriet.boot.rabbitmq.util.RabbitUtil;

/**
 * 消费者实体类
 * @author b_wangpei
 *
 */
public class ConsumerConfig {
	private final static Logger logger = LoggerFactory.getLogger(ConsumerConfig.class);
    private String queueName;
    private String routingKey;
    private int maxConsumer;
    
    private ConsumerSimpleMessageListenerContainer container;

    public int getMaxConsumer() {
        return maxConsumer;
    }

    public void setMaxConsumer(int maxConsumer) {
        this.maxConsumer = maxConsumer;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }
    public ConsumerConfig() {
    }
    public ConsumerConfig(String queueName, String routingKey, int maxConsumer) {
        this.queueName = queueName;
        this.routingKey = routingKey;
        this.maxConsumer = maxConsumer;
        initContainer();
    }
    /**
     * 使用默认配置构建消费者对象，相应的yml中一定要事先配置好相关的queues
     * @param queueName
     * 					队列名称
     * @param callback
     * 					消息接收回调
     */
    public ConsumerConfig(String queueName)  {
    	if(RabbitUtil.rbConfig != null) {
    		List<Queue> list = RabbitUtil.rbConfig.getQueues();
    		for(Queue q: list) {
    			if(q.getQueueName().equalsIgnoreCase(queueName.trim())) {
    				this.queueName = queueName;
    	            this.routingKey = queueName;
    	            this.maxConsumer = q.getMaxConsumer();
//    	            System.out.println("maxConsumer:::"+this.maxConsumer);
    	            initContainer();
    			}
    		}
    		if(this.queueName == null) {
    			logger.error("队列名称为:"+queueName+"的连接配置找不到");
    		}
    	}else {
    		logger.error("队列为："+queueName+"的连接配置不正确，无法解析queue对象");
    	}
        
    }
    private void initContainer(){
    	this.container = new ConsumerSimpleMessageListenerContainer();
        try {
			container.setConnectionFactory(RabbitUtil.connectionFactory());
			container.setQueueNames(this.queueName);
	        container.setMaxConcurrentConsumers(this.maxConsumer);
	        container.setConcurrentConsumers(1);  
	        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
//	        container.setMessageListener(new MessageListenerAdapter(o));
	       
//	        container.setMessageListener(new MessageListenerAdapter(new ConsumerHandler()));
//	        container.startConsumers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /*
     * 消费者停止接收队列的监听
     */
    public void stopListener() {
    	if(this.container != null) {
    		this.container.stop();
    	}
    }
    /**
     * 开启消息接收监听
     */
    public void startListener(Object o) {
    	if(this.container != null) {
    		try {
    			this.container.setMessageListener(o);
				this.container.startConsumers();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("startListener()失败，请检查队列连接配置！");
			};
    	}else {
    		logger.error("startListener()失败，当前container为null！");
    	}
    }
}
