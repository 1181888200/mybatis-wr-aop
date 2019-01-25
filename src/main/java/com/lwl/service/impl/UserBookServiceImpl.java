package com.lwl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lwl.domain.UserBook;
import com.lwl.mapper.UserBookMapper;
import com.lwl.service.IUserBookService;

@Service("userBookService")
public class UserBookServiceImpl implements IUserBookService {

	@Resource
	private UserBookMapper userBookMapper;
	
	@Override
	public List<UserBook> findUserBook() {
		return userBookMapper.findUserBook();
	}

	@Override
	public List<UserBook> getUserBook() {
		return userBookMapper.getUserBook();
	}

	@Override
	public int addUserBook(UserBook info) {
		return userBookMapper.addUserBook(info);
	}

}
