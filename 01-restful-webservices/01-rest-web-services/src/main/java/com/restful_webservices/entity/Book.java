package com.restful_webservices.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
                         CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "price")
    private int price;

    @Column(name = "published_year")
    private LocalDate publishedYear;

    @Column(name = "stock")
    private int stock;

    @OneToMany(cascade = CascadeType.ALL,
    mappedBy = "bookId")
    private List<Rating> ratings;

    public Book() {
    }

    public Book(String title, String description, int price, LocalDate publishedYear, int stock) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.publishedYear = publishedYear;
        this.stock = stock;
    }

    public Book(String title, Author author, String description, Category category, int price, LocalDate publishedYear, int stock, List<Rating> ratings) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.category = category;
        this.price = price;
        this.publishedYear = publishedYear;
        this.stock = stock;
        this.ratings = ratings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }




    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", publishedYear=" + publishedYear +
                ", stock=" + stock +
                ", ratings=" + ratings +
                '}';
    }
}
