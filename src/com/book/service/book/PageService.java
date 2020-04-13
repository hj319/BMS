package com.book.service.book;

import java.util.List;

import com.book.entity.BookInfo;
public interface PageService {

	int getCount();

	List<BookInfo> getBookData(int from, int pageSize);

	int getFindByBookNameCount(String bookName);

	List<BookInfo> getFindByBookNameData(int from, int pageSize, String bookName);

}
