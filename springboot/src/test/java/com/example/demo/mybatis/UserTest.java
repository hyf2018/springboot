package com.example.demo.mybatis;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;

@SpringBootTest
@Transactional
public class UserTest {
	@Autowired
	private UserMapper userMapper;

	@Test
	@Rollback(value = false)
	public void testUserMapper() {
		System.out.println("UserTest.testUserMapper()");
		// insert一条数据，并select出来验证
//		userMapper.insert("AAA", 20);
//		userMapper.insert("BBB", 99);
		int insert = userMapper.insert("CCC", 33);
		System.out.println("insert:"+insert);
//		userMapper.insert("DDD", 44);
		
		// 查询
//		User u = userMapper.findByName("AAA");
//		System.out.println(u);
//		
//		User u = userMapper.findById(2L);
//		System.out.println(u);
		
		List<User> users = userMapper.findAll();
		users.forEach(user->System.out.println(user));

		// update一条数据，并select出来验证
		users.forEach(u->{
			u.setAge(u.getAge()+1);
			int update = userMapper.update(u);
			System.out.println("update:"+update);
		});

		// 删除这条数据，并select验证
//		userMapper.delete(1L);
	}
}
