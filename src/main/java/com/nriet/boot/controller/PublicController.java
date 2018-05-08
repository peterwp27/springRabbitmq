package com.nriet.boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nriet.boot.services.TestReceiveService;
import com.nriet.boot.services.TestReceiveService2;
import com.nriet.boot.services.TestSendService;
import com.nriet.boot.services.TestSendService2;
import com.nriet.boot.services.UserDBSampleService;
/**
 * 
 * @author b_wangpei
 *
 */
@Controller
@RequestMapping("/front")
public class PublicController {
	private final static Logger logger = LoggerFactory.getLogger(PublicController.class);
	
	@Autowired
	private TestSendService testSendService;
	@Autowired
	private TestSendService2 testSendService2;
	@Autowired
	private TestReceiveService testReceiveService;
	@Autowired
	private TestReceiveService2 testReceiveService2;
	@Autowired
	private UserDBSampleService userDBSampleService;
	
	/**
	 * Controller 运行测试
	 * @return
	 */
	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}

	/**
	 * rabbitmq 队列发送接收测试
	 */
    @RequestMapping(value = "/send1", method = RequestMethod.GET)
    @ResponseBody
    public void test() {
    	try {
    		for(int i = 0 ; i < 10; i++) {
    			testSendService.send("test1::"+i);
        		testSendService2.send("test2::"+i);
    		}
//    		testReceiveService.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
     * Rabbitmq
     * 多线程并发情况下测试,消费者数量多的队列，消息接收的快
     */
    @RequestMapping(value = "/send2", method = RequestMethod.GET)
    @ResponseBody
    public void test2() {
     try {
    	 for(int i=0;i < 5;i++) {
    		 new Thread() {
    			 public void run(int t) {
    				 System.out.println("线程test:"+t+"执行了。。。。。。。");
    				 testReceiveService.start();//test1队列开启接收
    				 testReceiveService2.start();//test2队列开启接收
    			 }
    		 }.run(i);
    	 }
     }catch(Exception e) {
    	 e.printStackTrace();
     }
    }
    /**
     * 数据批量入库操作sample
     */
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    @ResponseBody
    public void test3() {
     try {
    	 int k = userDBSampleService.BatchInsert();
    	 System.out.println("==================="+k);
     }catch(Exception e) {
    	 e.printStackTrace();
     }
    }
}
