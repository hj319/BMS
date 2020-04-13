package com.book.commons;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtils {
	private static SqlSessionFactory fac;
	static {
		InputStream is;
		try {
			is = Resources.getResourceAsStream("mybatis-config.xml");
			//核心配置文件的读取
			fac=new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//创建执行对象的方法
	public static SqlSession createSqlSession() {
		SqlSession sqlSession = fac.openSession(false);
		return sqlSession;
	}
	//创建关闭执行对象的方法
	public static void closeSqlSession(SqlSession sqlSession)
	{
		if(sqlSession != null)
		{
			sqlSession.close();
		}
	}
}
