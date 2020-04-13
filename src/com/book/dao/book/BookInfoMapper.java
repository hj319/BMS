package com.book.dao.book;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.book.entity.BookInfo;

public interface BookInfoMapper {
	List<BookInfo> getcatebooK(@Param("cid") String cid);

	int addBookInfo(BookInfo book);

	BookInfo findById(@Param("id") int id);

	int updateBookInfo(BookInfo book);

	int deleteBookInfo(@Param("id")int id);
}
