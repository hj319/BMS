package com.book.service.category;

import java.util.List;

import com.book.entity.BookCategory;

public interface BookCategoryService {

	List<BookCategory> getCategory();

	boolean addCategory(String category);

	boolean deleteCategory(int id);

}
