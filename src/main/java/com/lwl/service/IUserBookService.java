package com.lwl.service;

import java.util.List;

import com.lwl.domain.UserBook;

public interface IUserBookService {

	
	int addUserBook(UserBook info);
	
	List<UserBook> findUserBook();
	
	List<UserBook> getUserBook();
	
}
