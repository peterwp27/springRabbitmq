package com.nriet.boot.rabbitmq.util;

import javax.annotation.PostConstruct;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.GenericMessageConverter;
import org.springframework.stereotype.Component;

import com.nriet.boot.rabbitmq.config.RabbitConfig;

/**
 * 
 * @author b_wangpei
 *
 */
@Component
public class RabbitUtil {
	
	@Autowired  
    private RabbitConfig rabbitConfig; 
	
	public static RabbitConfig rbConfig; 
	private static RabbitUtil rabbitUtil;
	
	@PostConstruct  
    public void init() {
		rabbitUtil = this;
		rabbitUtil.rbConfig = this.rabbitConfig;
    }
	/**
     * 初始化 ConnectionFactory
     *
     * @param addresses
     * @param username
     * @param password
     * @param vHost
     * @return
     * @throws Exception
     */
    public static ConnectionFactory connectionFactory(String addresses, String username, String password, String vHost) throws Exception {
        CachingConnectionFactory factoryBean = new CachingConnectionFactory();
        factoryBean.setVirtualHost(vHost);
        factoryBean.setAddresses(addresses);
        factoryBean.setUsername(username);
        factoryBean.setPassword(password);
        return factoryBean;
    }
    /**
     * 使用默认配置连接rabbitmq
     * @return
     * @throws Exception
     */
    public static ConnectionFactory connectionFactory() {
//    	System.out.println("queues:::"+rbConfig.getQueues().get(0).getQueueName());
    	CachingConnectionFactory	factoryBean = null;
    	try {
    		factoryBean = new CachingConnectionFactory();
            factoryBean.setVirtualHost(rbConfig.getVirtualhost());
            factoryBean.setAddresses(rbConfig.getHost());
            factoryBean.setUsername(rbConfig.getUsername());
            factoryBean.setPassword(rbConfig.getPassword());
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
        return factoryBean;
    }
    
   

    /**
     * 初始化 RabbitMessagingTemplate
     *
     * @param connectionFactory
     * @return
     */
    public static RabbitMessagingTemplate simpleMessageTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        RabbitMessagingTemplate rabbitMessagingTemplate = new RabbitMessagingTemplate();
        rabbitMessagingTemplate.setMessageConverter(new GenericMessageConverter());
        rabbitMessagingTemplate.setRabbitTemplate(template);
        return rabbitMessagingTemplate;
    }
}
