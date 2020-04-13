package com.book.dao.user;

import org.apache.ibatis.annotations.Param;

import com.book.entity.BookUser;

public interface BookUserMapper {
	//定义一个用户登录的方法
	int loginValidate(@Param("userId") String userId,@Param("userPsw") String userPsw);
	//定义一个用户注测的方法
	int saveUser(BookUser user);
	
}
