package com.book.dao.category;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.book.entity.BookCategory;

public interface BookCategoryMapper {

	List<BookCategory> getCategory();

	int addCategory(@Param("category") String category);

	int deleteCategory(@Param("id")int id);

}
