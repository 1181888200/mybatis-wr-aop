package com.lwl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lwl.aop.ReadOnlyConnection;
import com.lwl.domain.User;
import com.lwl.mapper.UserMapper;
import com.lwl.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;
	
	@ReadOnlyConnection
	@Override
	public List<User> findUser() {
		return userMapper.findUser();
	}

	@Override
	public int insertUser(User info) {
		return userMapper.insertUser(info);
	}

	@Override
	public int updUser(User info) {
		 
		return userMapper.updUser(info);
	}

	@Override
	public int delUser(long id) {
		return userMapper.delUser(id);
	}

	@Override
	public List<User> findList() {
		return userMapper.findList();
	}

}
