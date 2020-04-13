package com.book.service.category.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.book.commons.MybatisUtils;
import com.book.dao.category.BookCategoryMapper;
import com.book.dao.user.BookUserMapper;
import com.book.entity.BookCategory;
import com.book.service.category.BookCategoryService;

public class BookCategoryServiceImpl implements BookCategoryService {
	private SqlSession sqlSession;
	@Override
	public List<BookCategory> getCategory() {
		sqlSession=MybatisUtils.createSqlSession();
		BookCategoryMapper mapper=sqlSession.getMapper(BookCategoryMapper.class);
		List<BookCategory> list=mapper.getCategory();
		if(list!=null)
		{
			return list;
		}
		MybatisUtils.closeSqlSession(sqlSession);
		return null;
	}
	@Override
	public boolean addCategory(String category) {
		sqlSession=MybatisUtils.createSqlSession();
		BookCategoryMapper mapper=sqlSession.getMapper(BookCategoryMapper.class);
		int result=mapper.addCategory(category);
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
	@Override
	public boolean deleteCategory(int id) {
		sqlSession=MybatisUtils.createSqlSession();
		BookCategoryMapper mapper=sqlSession.getMapper(BookCategoryMapper.class);
		int result=mapper.deleteCategory(id);
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
