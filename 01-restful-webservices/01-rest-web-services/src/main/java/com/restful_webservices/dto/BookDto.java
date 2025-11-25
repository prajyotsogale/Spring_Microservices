package com.restful_webservices.dto;

import com.restful_webservices.entity.Author;
import com.restful_webservices.entity.Category;
import com.restful_webservices.entity.Rating;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

public class BookDto {

    private Integer id;
    private String title;
    private String authorName;
    private String description;
    private String categoryName;
    private int price;
    private LocalDate publishedYear;
    private int stock;
    private List<Integer> ratings;

    public BookDto() {
    }

    public BookDto(String title, String description, int price, LocalDate publishedYear, int stock) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.publishedYear = publishedYear;
        this.stock = stock;
    }

    public BookDto(String title, String authorName, String description, String categoryName, int price, LocalDate publishedYear, int stock, List<Integer> ratings) {
        this.title = title;
        this.authorName = authorName;
        this.description = description;
        this.categoryName = categoryName;
        this.price = price;
        this.publishedYear = publishedYear;
        this.stock = stock;
        this.ratings = ratings;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(LocalDate publishedYear) {
        this.publishedYear = publishedYear;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<Integer> getRatings() {
        return ratings;
    }

    public void setRatings(List<Integer> ratings) {
        this.ratings = ratings;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authorName='" + authorName + '\'' +
                ", description='" + description + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", price=" + price +
                ", publishedYear=" + publishedYear +
                ", stock=" + stock +
                ", ratings=" + ratings +
                '}';
    }
}
