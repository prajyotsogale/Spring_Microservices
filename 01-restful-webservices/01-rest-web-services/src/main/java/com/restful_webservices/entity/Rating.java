package com.restful_webservices.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "rating")
    private int rating;
    @Column(name = "review_text")
    private String reviewText;
    @Column(name = "date_created")
    private LocalDate dateCreated;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "book_id")
    private Book bookId;

    public Rating() {
    }

    public Rating(int rating, String reviewText, LocalDate dateCreated) {
        this.rating = rating;
        this.reviewText = reviewText;
        this.dateCreated = dateCreated;
    }

    public Rating(int rating, String reviewText, LocalDate dateCreated, Book bookId) {
        this.rating = rating;
        this.reviewText = reviewText;
        this.dateCreated = dateCreated;
        this.bookId = bookId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Book getBookId() {
        return bookId;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", rating=" + rating +
                ", reviewText='" + reviewText + '\'' +
                ", dateCreated=" + dateCreated +
                ", bookId=" + bookId +
                '}';
    }
}
