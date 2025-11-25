package com.restful_webservices.dto;

import com.restful_webservices.entity.Book;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

public class RatingDto {

    private Integer id;
    private int rating;
    private String reviewText;
    private LocalDate dateCreated;
    private Integer bookId;

    public RatingDto() {
    }

    public RatingDto(int rating, String reviewText, LocalDate dateCreated) {
        this.rating = rating;
        this.reviewText = reviewText;
        this.dateCreated = dateCreated;
    }

    public RatingDto(int rating, String reviewText, LocalDate dateCreated, Integer bookId) {
        this.rating = rating;
        this.reviewText = reviewText;
        this.dateCreated = dateCreated;
        this.bookId = bookId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "RatingDto{" +
                "id=" + id +
                ", rating=" + rating +
                ", reviewText='" + reviewText + '\'' +
                ", dateCreated=" + dateCreated +
                ", bookId=" + bookId +
                '}';
    }
}
