package com.book.service.book;

import java.util.List;

import com.book.entity.BookInfo;

public interface BookInfoService {

	List<BookInfo> getcatebook(String cid);

	boolean addBookInfo(BookInfo book);

	BookInfo findById(int id);

	boolean updateBookInfo(BookInfo book);

	boolean deleteBookInfo(int id);

}
