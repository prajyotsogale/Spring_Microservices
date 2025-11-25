package com.restful_webservices.mapper;


import com.restful_webservices.dto.CategoryDto;
import com.restful_webservices.entity.Category;

import java.util.List;

public interface CategoryMapper {
    CategoryDto convertCategoryToDto(Category category);
    Category convertCategoryDtoToCategory(CategoryDto categoryDto);
    List<CategoryDto> convertListOfCategoriesToDto(List<Category> categories);
}
