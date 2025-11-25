package com.restful_webservices.service;

import com.restful_webservices.dto.BookDto;
import com.restful_webservices.entity.Book;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    //core crud
    BookDto save(BookDto bookDto);
    List<BookDto> findAll();
    BookDto findBookById(int id);
    BookDto updateBook(BookDto book);
    void deleteBookById(int id);

    //search feature
    BookDto searchByTitle(String title);
    List<BookDto> searchByPublishedDate(LocalDate date);

    //filter books
    List<BookDto> filterBooksByRating(int rating);
    List<BookDto> filterByAvailableStocks();



}
