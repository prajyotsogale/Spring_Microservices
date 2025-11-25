package com.restful_webservices.dto;


import java.util.List;

public class CategoryDto {

    private Integer id;
    private String categoryName;
    private String description;
    private List<String> bookTitles;

    public CategoryDto() {
    }

    public CategoryDto(String categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
    }

    public CategoryDto(String categoryName, String description, List<String> bookTitles) {
        this.categoryName = categoryName;
        this.description = description;
        this.bookTitles = bookTitles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getBookTitles() {
        return bookTitles;
    }

    public void setBookTitles(List<String> bookTitles) {
        this.bookTitles = bookTitles;
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                ", bookNames=" + bookTitles +
                '}';
    }
}
