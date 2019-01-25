package com.lwl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lwl.domain.User;

public interface UserMapper {

	
	@Select("SELECT * FROM test_user")
	List<User> findUser();
	

    @Insert({ "insert into test_user(name,sex,job) values(#{name}, #{sex}, #{job})" })
	int insertUser(User info);
	
    
    @Update({ "update  test_user set name = #{name} , sex = #{sex},job = #{job} where id = #{id}" })
    int updUser(User info);
    
    
    @Delete({ "delete from test_user  where id = #{id}" })
    int delUser(long id);
    
    @Select("SELECT * FROM test_user")
    List<User> findList();
    
    
    
}
