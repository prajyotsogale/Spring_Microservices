package com.restful_webservices.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.restful_webservices.dto.BookDto;
import com.restful_webservices.dto.CategoryDto;
import com.restful_webservices.entity.Book;
import com.restful_webservices.entity.Category;
import com.restful_webservices.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CategoryController {
    private CategoryService categoryService;
    private ObjectMapper objectMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, ObjectMapper objectMapper){
        this.categoryService = categoryService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/categories")
    public List<CategoryDto> getCategories(){
        return categoryService.findAllCategory();
    }
    @GetMapping("/categories/{id}")
    public CategoryDto getCategory(@PathVariable int id){
        return categoryService.getCategoryById(id);
    }

    @PostMapping("/categories")
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto){
        return categoryService.addCategory(categoryDto);
    }
    @PutMapping("/categories/{id}")
    public CategoryDto updateCategory(@PathVariable int id, @RequestBody CategoryDto updatedCategoryDto){
        updatedCategoryDto.setId(id);
        return categoryService.updateCategory(updatedCategoryDto);
    }
    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable int id){
        categoryService.deleteCategory(id);
    }
    //patch
    @PatchMapping("/categories/{id}")
    public CategoryDto partialUpdateCategory(@PathVariable int id, @RequestBody Map<String, Object> patchPayload){
        CategoryDto categoryDto = categoryService.getCategoryById(id);
        if(patchPayload.containsKey("id")){
            throw new RuntimeException("don't write id in it");
        }
        CategoryDto patchedCategory = apply(patchPayload, categoryDto);
        return categoryService.updateCategory(patchedCategory);
    }
    public CategoryDto apply(Map<String, Object> patchPayload, CategoryDto categoryDto){
        //convert both to object node
        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);
        ObjectNode categoryNode = objectMapper.convertValue(categoryDto, ObjectNode.class);

        //merge
        categoryNode.setAll(patchNode);
        //convert to returning type
        return objectMapper.convertValue(categoryNode, CategoryDto.class);
    }

    @GetMapping("categories/{id}/books")
    public List<BookDto> getBooksByCategoryId(@PathVariable int id){
        return categoryService.getBooksByCategoryId(id);
    }


}
