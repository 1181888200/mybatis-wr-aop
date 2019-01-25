package com.lwl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.lwl.domain.UserBook;

public interface UserBookMapper {

	@Insert("insert into test_user_book(title,author,totalPage) values (#{title} , #{author},#{totalPage})")
	int addUserBook(UserBook info);
	
	@Select("select * from test_user_book")
	List<UserBook> findUserBook();
	
	@Select("select * from test_user_book")
	List<UserBook> getUserBook();
	
}
