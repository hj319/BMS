package com.book.service.user.impl;

import org.apache.ibatis.session.SqlSession;

import com.book.commons.MybatisUtils;
import com.book.dao.user.BookUserMapper;
import com.book.entity.BookUser;
import com.book.service.user.BookUserService;

public class BookUserServiceImpl implements BookUserService {
	private SqlSession sqlSession;
	@Override
	public boolean loginValidate(String userId, String userPsw) {
		sqlSession=MybatisUtils.createSqlSession();
		BookUserMapper mapper=sqlSession.getMapper(BookUserMapper.class);
		int result=mapper.loginValidate(userId, userPsw);
		
		if(result>0)
		{
			MybatisUtils.closeSqlSession(sqlSession);
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean saveUser(BookUser user) {
		sqlSession=MybatisUtils.createSqlSession();
		BookUserMapper mapper=sqlSession.getMapper(BookUserMapper.class);
		int result=mapper.saveUser(user);
		if(result>0)
		{
			sqlSession.commit();
			MybatisUtils.closeSqlSession(sqlSession);
			return true;
		}
		else {
			sqlSession.rollback();
			MybatisUtils.closeSqlSession(sqlSession);
			return false;
		}
		
	}

}
