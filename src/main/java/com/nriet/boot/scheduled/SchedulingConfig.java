package com.nriet.boot.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
/**
 * 定时器demo，采用cron注解
 * @author b_wangpei
 *
 */
@Configuration
@EnableScheduling
public class SchedulingConfig {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Scheduled(cron = "0 0/10 * * * ?") // 每10分钟执行一次
    public void getToken() {
    	System.out.println("getToken定时任务启动");
//        logger.info("getToken定时任务启动");
    }
}
