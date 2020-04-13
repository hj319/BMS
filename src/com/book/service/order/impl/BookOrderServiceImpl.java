package com.book.service.order.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.book.commons.MybatisUtils;
import com.book.dao.order.BookOrdersMapper;
import com.book.entity.BookOrders;
import com.book.service.order.BookOrderService;

public class BookOrderServiceImpl implements BookOrderService {
	private SqlSession sqlSession;
	@Override
	public List<BookOrders> getOrderList(String uid) {
		sqlSession=MybatisUtils.createSqlSession();
		BookOrdersMapper mapper=sqlSession.getMapper(BookOrdersMapper.class);
		List<BookOrders> list=mapper.getOrderList(uid);
		if(list!=null)
		{
			return list;
		}
		MybatisUtils.closeSqlSession(sqlSession);
		return null;
	}
	@Override
	public boolean addOrder(BookOrders order) {
		sqlSession=MybatisUtils.createSqlSession();
		BookOrdersMapper mapper=sqlSession.getMapper(BookOrdersMapper.class);
		int result =mapper.saveOrder(order);
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
	public boolean updateOrder(String oid, int count, double curPrice) {
		sqlSession=MybatisUtils.createSqlSession();
		BookOrdersMapper mapper=sqlSession.getMapper(BookOrdersMapper.class);
		int result=mapper.updateOrder(oid,count,curPrice);
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
