package com.book.dao.book;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.book.entity.BookInfo;


public interface PageMapper {
	
	int getCount();
	
	List<BookInfo> getBookData(@Param("from") int from,@Param("pageSize") int pageSize);

	int getFindByBookNameCount(@Param("bookName")String bookName);

	List<BookInfo> getFindByBookNameData(@Param("from") int from,@Param("pageSize") int pageSize,@Param("bookName") String bookName);
}
