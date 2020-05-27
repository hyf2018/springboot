package com.example.demo.mybatis;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper2;

@SpringBootTest
@Transactional
public class UserTest2 {
	@Autowired
	private UserMapper2 userMapper;

	@Test
	@Rollback(value = false)
	public void testUserMapper() {
		System.out.println("UserTest2.testUserMapper()");
		// insert一条数据，并select出来验证
//		userMapper.insert("AAA", 20);
//		userMapper.insert("BBB", 99);
		User u = userMapper.findByName("AAA");
		System.out.println(u);
//		List<User> users = userMapper.findAll();
//		users.forEach(u->System.out.println(u));

		// update一条数据，并select出来验证
//		users.forEach(u->{
//			u.setAge(u.getAge()+1);
//			userMapper.update(u);
//		});

		// 删除这条数据，并select验证
//		userMapper.delete(8L);
	}
}
