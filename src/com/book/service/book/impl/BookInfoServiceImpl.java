package com.book.service.book.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.book.commons.MybatisUtils;
import com.book.dao.book.BookInfoMapper;
import com.book.entity.BookInfo;
import com.book.service.book.BookInfoService;

public class BookInfoServiceImpl implements BookInfoService {
	private SqlSession sqlSession;
	@Override
	public List<BookInfo> getcatebook(String cid) {
		sqlSession = MybatisUtils.createSqlSession();
		BookInfoMapper mapper=sqlSession.getMapper(BookInfoMapper.class);
		List<BookInfo> list=mapper.getcatebooK(cid);
		if(list!=null)
		{
			return list;
		}
		MybatisUtils.closeSqlSession(sqlSession);
		return null;
	}
	@Override
	public boolean addBookInfo(BookInfo book) {
		sqlSession = MybatisUtils.createSqlSession();
		BookInfoMapper mapper=sqlSession.getMapper(BookInfoMapper.class);
		int result=mapper.addBookInfo(book);
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
	public BookInfo findById(int id) {
		sqlSession = MybatisUtils.createSqlSession();
		BookInfoMapper mapper=sqlSession.getMapper(BookInfoMapper.class);
		BookInfo book=mapper.findById(id);
		
		if(book!=null)
		{
			return book;
		}
		MybatisUtils.closeSqlSession(sqlSession);
		return null;
	}
	@Override
	public boolean updateBookInfo(BookInfo book) {
		sqlSession = MybatisUtils.createSqlSession();
		BookInfoMapper mapper=sqlSession.getMapper(BookInfoMapper.class);
		int result=mapper.updateBookInfo(book);
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
	public boolean deleteBookInfo(int id) {
		sqlSession = MybatisUtils.createSqlSession();
		BookInfoMapper mapper=sqlSession.getMapper(BookInfoMapper.class);
		int result=mapper.deleteBookInfo(id);
		MybatisUtils.closeSqlSession(sqlSession);
		if(result>0)
		{
			return true;
		}
		else {
			return false;
		}
	}

}
