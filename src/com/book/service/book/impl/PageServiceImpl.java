package com.book.service.book.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.book.commons.MybatisUtils;
import com.book.dao.book.PageMapper;
import com.book.entity.BookInfo;
import com.book.service.book.PageService;

public class PageServiceImpl implements PageService {
	private PageMapper mapper;
	/**
	 * ���ͼ�����������ķ���
	 */
	@Override
	public int getCount() {
		SqlSession sqlSession=MybatisUtils.createSqlSession();
		mapper=sqlSession.getMapper(PageMapper.class);
		int result=mapper.getCount();
		MybatisUtils.closeSqlSession(sqlSession);
		return result;
	}
	/**
	 * ͨ��ÿҳ��ʾ���������͵�ǰҳ��ȥ��ѯ��ҳ����
	 */
	@Override
	public List<BookInfo> getBookData(int from, int pageSize) {
		SqlSession sqlSession=MybatisUtils.createSqlSession();
		mapper=sqlSession.getMapper(PageMapper.class);
		List<BookInfo> bookList=mapper.getBookData(from, pageSize);
		MybatisUtils.closeSqlSession(sqlSession);
		return bookList;
	}
	@Override
	public int getFindByBookNameCount(String bookName) {
		SqlSession sqlSession=MybatisUtils.createSqlSession();
		mapper=sqlSession.getMapper(PageMapper.class);
		int result=mapper.getFindByBookNameCount(bookName);
		MybatisUtils.closeSqlSession(sqlSession);
		return result;
	}
	@Override
	public List<BookInfo> getFindByBookNameData(int from, int pageSize, String bookName) {
		SqlSession sqlSession=MybatisUtils.createSqlSession();
		mapper=sqlSession.getMapper(PageMapper.class);
		List<BookInfo> bookList=mapper.getFindByBookNameData(from, pageSize,bookName);
		MybatisUtils.closeSqlSession(sqlSession);
		return bookList;
	}

}
