package com.nriet.boot.rabbitmq.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * 从配置文件读取rabbitmq配置信息
 * @author b_wangpei
 *
 */
@Component
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitConfig {
	private String host;
	private String username;
	private String password;
	private String virtualhost;
	private List<Queue> queues = new ArrayList<Queue>();
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVirtualhost() {
		return virtualhost;
	}

	public void setVirtualhost(String virtualhost) {
		this.virtualhost = virtualhost;
	}

	public List<Queue> getQueues() {
		return queues;
	}

	public void setQueues(List<Queue> queues) {
		this.queues = queues;
	}


}
