package com.book.service.user;


import org.apache.ibatis.annotations.Param;
import com.book.entity.BookUser;

public interface BookUserService {
	//定义一个用户登录的方法
		boolean loginValidate(@Param("userId") String userId,@Param("userPsw") String userPsw);
		//定义一个用户注测的方法
		boolean saveUser(BookUser user);
}
