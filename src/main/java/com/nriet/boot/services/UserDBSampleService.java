package com.nriet.boot.services;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nriet.boot.dao.UserDao;
import com.nriet.boot.model.User;
import com.nriet.framework.springjpa.BasicJpaService;
/**
 * 数据库操作简单案例
 * @author b_wangpei
 *
 */
@Service
public class UserDBSampleService extends BasicJpaService<User, Integer, UserDao> {
	/**
	 * 一个批量插入的小例子
	 * @return
	 */
	public int BatchInsert() {
		List<User> entities = new LinkedList<User>();
		for(int t = 0; t < 5 ; t++) {
			User u = new User();
//			User u = this.getOne(12 + t);
//			u.setId(t+12);
			u.setUsername("username"+t);
			u.setNickName("nickname"+t);
			u.setPassword("222222");
			u.setRegisterDate(new Date());
			u.setSex(0);
			entities.add(u);
		}
		List<User> Rslist = this.save(entities);
		return Rslist.size();
	}
}
