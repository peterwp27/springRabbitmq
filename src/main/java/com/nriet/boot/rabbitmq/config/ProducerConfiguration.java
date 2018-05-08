package com.nriet.boot.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.nriet.boot.rabbitmq.util.RabbitUtil;

/**
 * producer实体控制类，用于发送报文到指定的队列
 * @author b_wangpei
 *
 */
public class ProducerConfiguration {
    private String queueName;
    private String routingKey;
    private RabbitTemplate rabbitTemplate;
    private String exchange;
    
    public ProducerConfiguration() {

    }

    public ProducerConfiguration(String exchange,String queueName, String routingKey){
        this.queueName = queueName;
        this.routingKey = routingKey;
        this.exchange=exchange;
        SetRabbitAdmin();
//        admin.setAutoStartup(true);
    }
    
    public ProducerConfiguration(String exchange,String queueName){
        this.queueName = queueName;
        this.routingKey = queueName;
        this.exchange = exchange;
        SetRabbitAdmin();
    }
    public ProducerConfiguration(String queueName){
        this.queueName = queueName;
        this.routingKey = queueName;
        this.exchange = "default.topic";
        SetRabbitAdmin();
    }
    
    private void SetRabbitAdmin() {
    	try {
			this.rabbitTemplate = rabbitTemplate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        RabbitAdmin admin = new RabbitAdmin(this.rabbitTemplate.getConnectionFactory());
        admin.declareQueue(new Queue(this.queueName));
        admin.declareExchange(new TopicExchange(exchange));
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }
    public String getQueueName() {
        return queueName;
    }

    public String getRoutingKey() {
        return routingKey;
    }
    public RabbitTemplate rabbitTemplate() throws Exception {
        RabbitTemplate template = new RabbitTemplate(RabbitUtil.connectionFactory());
        template.setRoutingKey(this.routingKey);
        template.setQueue(this.queueName);
        return template;
    }

    public void send(Object o) {
        this.rabbitTemplate.convertAndSend(o);
    }

    public void send(String exchange,String routingKey,Object msg) {
        this.rabbitTemplate.convertAndSend(exchange,routingKey,msg);
    }
}
