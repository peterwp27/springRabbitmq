/**
 * 此包为rabbit的sample服务以及数据库批量插入的示例，可结合具体环境修改测试
 * 
 * 
 * TestSendService.java----test1队列发送服务层
 * TestSendService2.java----test2队列发送服务层
 * 
 * TestReceiveService.java----test1队列消息接收服务层
 * TestReceiveService2.java----test2队列消息接收服务层
 * 
 * UserDBSampleService.java----数据库批量操作示例（需要在model包完成ORM映射，在dao包完成jpa接口建立即可，所有通用功能已经在BasicJpaService实现，BasicJpaService中实现了直接执行原生sql语句的方法供拓展功能）
 */
/**
 * @author b_wangpei
 * 2018-4-3
 */
package com.nriet.boot.services;