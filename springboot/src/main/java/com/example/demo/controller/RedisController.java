package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.util.RedisUtil;

@RestController
@RequestMapping("/redis")
public class RedisController {
	public static final Logger log = LoggerFactory.getLogger(RedisController.class);

	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private UserMapper userMapper;

	@RequestMapping(value = "/getUser/{id}")
	public String getUser(@PathVariable(value = "id") String id) {
		// 查询缓存中是否存在
		String key = "user_" + id;
		boolean hasKey = redisUtil.hasKey(key);
		User user = null;
		if (hasKey) {
			// 获取缓存
			user = (User) redisUtil.get(key);
			log.info("从缓存获取的数据:" + user);
		} else {
			// 从数据库中获取信息
			log.info("从数据库中获取数据");
			user = userMapper.findById(Long.valueOf(id));
			// 数据插入缓存（set中的参数含义：key值，user对象，缓存时间(秒)）
			redisUtil.set(key, user, 600L);
			log.info("数据插入缓存：" + user);
		}
		return user == null ? ("找不到记录：" + id) : user.toString();
	}
}