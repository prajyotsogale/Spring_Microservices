package com.restful_webservices.service;

import com.restful_webservices.dto.BookDto;
import com.restful_webservices.dto.RatingDto;
import com.restful_webservices.entity.Book;
import com.restful_webservices.entity.Rating;

import java.util.List;

public interface RatingService {
    RatingDto addRatingToBook(RatingDto ratingDto, int bookId);
    RatingDto updateRating(RatingDto ratingDto);
    void deleteRatingById(int id);
    RatingDto getRatingById(int id);
    List<RatingDto> findAllRatingsByBookId(int id);
    double getAverageRating(BookDto book);
    double getAverageRating(Book book);
    List<BookDto> sortBooksByRating(List<BookDto> books);
}
