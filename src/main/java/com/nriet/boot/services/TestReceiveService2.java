package com.nriet.boot.services;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Service;

import com.nriet.boot.rabbitmq.basic.BaseReceiveService;
import com.nriet.framework.util.PropertiesUtil;
import com.rabbitmq.client.Channel;

/**
 * 针对每个队列建立一个接收服务 父类中包含一个consumer对象，可以直接在service中使用进行消息接收及启停操作
 * 
 * @author b_wangpei
 */
@Service
public class TestReceiveService2 extends BaseReceiveService {
	private static PropertiesUtil propertis = PropertiesUtil.Instances("properties/config.properties");

	public TestReceiveService2() {
		super(propertis.get("rabbitmq.queues2"));
	}
	/**
	 * 如果想让任务在应用启动后自动启动，加上下面这个方法即可， 否则需要在业务中调用相应的启动或关闭方法对接收监听进行操作
	 */
	@PostConstruct
	public void autoStart() {
		// 放开此方法时需要解除try start()的注释；
		try {
			 start();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void start() {
		consumer.startListener(new ChannelAwareMessageListener() {
			@Override
			public void onMessage(Message message, Channel channel) throws Exception {
				byte[] body = message.getBody();
				System.out.println("receive msg TestReceiveService2: " + new String(body));
				channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); // 确认消息成功消费
//				channel.basicReject(message.getMessageProperties().getDeliveryTag(), true); // true 表示该消息重新放回队列头，值为 false 表示放弃这条消息
			};
		});
	}

}
