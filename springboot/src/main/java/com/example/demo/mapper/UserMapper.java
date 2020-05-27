package com.example.demo.mapper;

import java.util.List;

import com.example.demo.entity.User;

/**
 * 在接口中定义数据操作,sql写在xml配置文件中
 */
public interface UserMapper {

	User findByName(String name);

	User findById(Long id);

	List<User> findAll();

	int insert(String name, Integer age);

	int update(User user);

	int delete(Long id);

}
