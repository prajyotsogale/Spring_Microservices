package com.restful_webservices.repository;

import com.restful_webservices.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findByTitleIgnoreCase(String title);
    List<Book> findByAuthorId(int authorId);
    List<Book> findByCategoryCategoryNameIgnoreCase(String categoryName);
    List<Book> findByPublishedYear(LocalDate date);
    List<Book> findByStockGreaterThan(int stockThreshold);
}
