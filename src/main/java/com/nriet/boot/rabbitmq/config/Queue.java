package com.nriet.boot.rabbitmq.config;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * 队列实体类，可扩展  目前支持最大消费者数的配置
 * @author b_wangpei
 *
 */
public class Queue{
	private String queueName;
	private int maxConsumer = 1;
	private int priority;
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	public int getMaxConsumer() {
		return maxConsumer;
	}
	public void setMaxConsumer(int maxConsumer) {
		this.maxConsumer = maxConsumer;
	}
	/**
	 * 根据优先级冒泡倒序排列
	 * @param queues
	 * @return
	 */
	public static List<Queue> sort(List<Queue> queues){
		Queue maxq,tempq;
		List<Queue> newQueues = new ArrayList<Queue>();
        for(int i = 0 ;i < queues.size();i++) {
        	maxq = queues.get(i);
        	for(int j = i + 1;j < queues.size();j++) {
        		Queue q = queues.get(j);
        		if(q.getPriority() > maxq.getPriority()) {
        			tempq = maxq;
        			maxq = q;
        			queues.set(j, tempq);
        		}
        	}
        	newQueues.add(maxq);
        }
		return newQueues;
	}
	public static void main(String[] args) {
		List<Queue> queues = new ArrayList<Queue>();
//		for(int i = 0;i < 10;i++) {
			Queue q = new Queue();
			q.setPriority(4);
			q.setQueueName("sss:"+4);
			queues.add(q);
//		}
			Queue q1 = new Queue();
			q1.setPriority(7);
			q1.setQueueName("sss:"+7);
			queues.add(q1);
			Queue q2 = new Queue();
			q2.setPriority(2);
			q2.setQueueName("sss:"+2);
			queues.add(q2);
			Queue q3 = new Queue();
			q3.setPriority(9);
			q3.setQueueName("sss:"+9);
			queues.add(q3);
			Queue q4 = new Queue();
			q4.setPriority(5);
			q4.setQueueName("sss:"+5);
			queues.add(q4);
		queues = Queue.sort(queues);
		for(int j = 0;j < queues.size();j++) {
			Queue s = queues.get(j);
			System.out.println(s.getQueueName());
		}
	}
}
