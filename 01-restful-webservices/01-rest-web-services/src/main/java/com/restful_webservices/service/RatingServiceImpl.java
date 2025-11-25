package com.restful_webservices.service;

import com.restful_webservices.dto.BookDto;
import com.restful_webservices.dto.RatingDto;
import com.restful_webservices.entity.Book;
import com.restful_webservices.entity.Rating;
import com.restful_webservices.mapper.RatingMapper;
import com.restful_webservices.mapper.RatingMapperImpl;
import com.restful_webservices.repository.BookRepository;
import com.restful_webservices.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RatingServiceImpl implements RatingService{

    private RatingRepository ratingRepository;
    private BookRepository bookRepository;
    private RatingMapper ratingMapper;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository, BookRepository bookRepository,
                             RatingMapper ratingMapper){
        this.ratingMapper = ratingMapper;
        this.ratingRepository = ratingRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public RatingDto addRatingToBook(RatingDto ratingDto, int bookId) {
        Rating rating = ratingMapper.convertRatingDtoToRating(ratingDto);
        if(rating == null){
            throw new RuntimeException("Rating can't be null");
        }
        Optional<Book> tempBook  = bookRepository.findById(bookId);
        if(tempBook.isEmpty()){
            throw new RuntimeException("can't find book with id: "+bookId);
        }
        Book book = tempBook.get();
        rating.setBookId(book);
        Rating savedRating = ratingRepository.save(rating);
        book.getRatings().add(savedRating);
        return ratingMapper.convertRatingToRatingDto(savedRating);
    }

    @Override
    public RatingDto updateRating(RatingDto ratingDto) {
        Rating rating = ratingMapper.convertRatingDtoToRating(ratingDto);
        RatingDto ratingDtoDb = ratingMapper.convertRatingToRatingDto(ratingRepository.save(rating));
        return ratingDtoDb;
    }

    @Override
    public void deleteRatingById(int id) {
        ratingRepository.deleteById(id);
    }

    @Override
    public RatingDto getRatingById(int id) {
        Optional<Rating> tempRating = ratingRepository.findById(id);
        if(tempRating.isEmpty()){
            throw new RuntimeException("Rating not found by id: "+id);
        }
        return ratingMapper.convertRatingToRatingDto(tempRating.get());
    }

    @Override
    public List<RatingDto> findAllRatingsByBookId(int id) {
        return ratingMapper.convertListOfRatingsToRatingDto(ratingRepository.findByBookId_Id(id));
    }

    @Override
    public double getAverageRating(BookDto book) {
        List<Rating> ratingList = ratingRepository.findByBookId_Id(book.getId());
        double numerator = 0;
        double denominator = 0;
        for (Rating rating: ratingList){
            numerator += rating.getRating();
            denominator += 1;

        }
        return denominator==0? 0.0: numerator/denominator;
    }
    @Override
    public double getAverageRating(Book book) {
        List<Rating> ratingList = ratingRepository.findByBookId_Id(book.getId());
        double numerator = 0;
        double denominator = 0;
        for (Rating rating: ratingList){
            numerator += rating.getRating();
            denominator += 1;

        }
        return denominator==0? 0.0: numerator/denominator;
    }

    @Override
    public List<BookDto> sortBooksByRating(List<BookDto> books) {

        List<Map.Entry<Double, BookDto>> tempList =  new ArrayList<>();
        for (BookDto book: books){
            double average = getAverageRating(book);
            tempList.add(Map.entry(average, book));
        }

        //sort by average
        tempList.sort((a,b) -> Double.compare(b.getKey(), a.getKey()));

        //change it to List

        List<BookDto> bookList = new ArrayList<>();

        for (Map.Entry<Double, BookDto> tempEntry : tempList){
            bookList.add(tempEntry.getValue());
        }

        return bookList;
    }
}
