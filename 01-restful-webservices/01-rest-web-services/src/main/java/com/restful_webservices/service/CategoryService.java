package com.restful_webservices.service;

import com.restful_webservices.dto.BookDto;
import com.restful_webservices.dto.CategoryDto;
import com.restful_webservices.entity.Book;
import com.restful_webservices.entity.Category;

import java.util.List;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto);
    List<CategoryDto> findAllCategory();
    CategoryDto getCategoryById(int id);
    List<BookDto> getBooksByCategoryId(int id);
    CategoryDto updateCategory(CategoryDto categoryDto);
    void deleteCategory(int id);
}
