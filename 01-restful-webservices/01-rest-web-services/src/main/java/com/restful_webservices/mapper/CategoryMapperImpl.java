package com.restful_webservices.mapper;

import com.restful_webservices.dto.CategoryDto;
import com.restful_webservices.entity.Book;
import com.restful_webservices.entity.Category;
import com.restful_webservices.repository.BookRepository;
import com.restful_webservices.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class CategoryMapperImpl implements CategoryMapper{

    private CategoryRepository categoryRepository;
    private BookRepository bookRepository;

    @Autowired
    public CategoryMapperImpl(CategoryRepository categoryRepository,
                              BookRepository bookRepository){
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public CategoryDto convertCategoryToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto(category.getCategoryName(), category.getDescription());
        categoryDto.setId(category.getId());
        if(category.getBookList() != null){
            List<String> bookTitle = new ArrayList<>();
            for (Book book: category.getBookList()){
                bookTitle.add(book.getTitle());
            }
            categoryDto.setBookTitles(bookTitle);
        }
        return categoryDto;

    }

    @Override
    public Category convertCategoryDtoToCategory(CategoryDto categoryDto) {
        Category category;

        if(categoryDto.getId() != null){
            category = categoryRepository.findById(categoryDto.getId())
                    .orElseThrow(()-> new RuntimeException("Category not found"));

        }else{
            category = new Category();
        }
        category.setCategoryName(categoryDto.getCategoryName());
        category.setDescription(categoryDto.getDescription());
        return category;
    }

    @Override
    public List<CategoryDto> convertListOfCategoriesToDto(List<Category> categories) {
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category category: categories){
            categoryDtos.add(convertCategoryToDto(category));
        }
        return categoryDtos;
    }
}
