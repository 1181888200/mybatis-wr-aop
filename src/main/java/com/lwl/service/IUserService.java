package com.lwl.service;

import java.util.List;

import com.lwl.domain.User;

public interface IUserService {

	List<User> findUser();
	
	int insertUser(User info);
	
	int updUser(User info);
	
	int delUser(long id);
	
	List<User> findList();
	
}
