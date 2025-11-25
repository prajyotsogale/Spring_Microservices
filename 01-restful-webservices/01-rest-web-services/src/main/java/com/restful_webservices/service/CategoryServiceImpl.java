package com.restful_webservices.service;

import com.restful_webservices.dto.BookDto;
import com.restful_webservices.dto.CategoryDto;
import com.restful_webservices.entity.Book;
import com.restful_webservices.entity.Category;
import com.restful_webservices.mapper.BookMapper;
import com.restful_webservices.mapper.BookMapperImpl;
import com.restful_webservices.mapper.CategoryMapper;
import com.restful_webservices.repository.BookRepository;
import com.restful_webservices.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;
    private BookRepository bookRepository;
    private CategoryMapper categoryMapper;
    private BookMapper bookMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, BookRepository bookRepository,
                               CategoryMapper categoryMapper, BookMapper bookMapper){
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = categoryMapper.convertCategoryDtoToCategory(categoryDto);
       return categoryMapper.convertCategoryToDto(categoryRepository.save(category));

    }

    @Override
    public List<CategoryDto> findAllCategory() {
        return categoryMapper.convertListOfCategoriesToDto(categoryRepository.findAll());
    }

    @Override
    public CategoryDto getCategoryById(int id) {
        Optional<Category> tempCategory = categoryRepository.findById(id);
        if(tempCategory.isEmpty()){
            throw new RuntimeException("can't find category by id: "+id);
        }

        return categoryMapper.convertCategoryToDto(tempCategory.get());
    }

    @Override
    public List<BookDto> getBooksByCategoryId(int id) {
        Category theCategory = categoryMapper.convertCategoryDtoToCategory(getCategoryById(id));
        List<Book> dbBooks = bookRepository.findByCategoryCategoryNameIgnoreCase(theCategory.getCategoryName());
        return bookMapper.convertListOfBooksToDto(dbBooks);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        return addCategory(categoryDto);
    }

    @Override
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
}
