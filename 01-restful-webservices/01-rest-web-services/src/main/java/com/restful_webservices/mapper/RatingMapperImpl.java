package com.restful_webservices.mapper;

import com.restful_webservices.dto.RatingDto;
import com.restful_webservices.entity.Book;
import com.restful_webservices.entity.Rating;
import com.restful_webservices.repository.BookRepository;
import com.restful_webservices.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
@Configuration
public class RatingMapperImpl implements RatingMapper{

    private RatingRepository ratingRepository;
    private BookRepository bookRepository;

    @Autowired
    public RatingMapperImpl(RatingRepository ratingRepository, BookRepository bookRepository){
        this.bookRepository = bookRepository;
        this.ratingRepository = ratingRepository;
    }

    @Override
    public RatingDto convertRatingToRatingDto(Rating rating) {
        RatingDto ratingDto = new RatingDto();
        ratingDto.setId(rating.getId());
        ratingDto.setRating(rating.getRating());
        ratingDto.setReviewText(rating.getReviewText());
        ratingDto.setDateCreated(rating.getDateCreated());
        ratingDto.setBookId(rating.getBookId().getId());
        return ratingDto;
    }

    @Override
    public Rating convertRatingDtoToRating(RatingDto ratingDto) {
        Rating rating;
        if(ratingDto.getId() != null){
             rating = ratingRepository.findById(ratingDto.getId())
                    .orElseThrow(()-> new RuntimeException("Rating not found"));
        }else{
            rating = new Rating();
        }
        rating.setDateCreated(ratingDto.getDateCreated());
        rating.setRating(ratingDto.getRating());
        rating.setReviewText(ratingDto.getReviewText());
        if(ratingDto.getBookId() != null){
            Book book = bookRepository.findById(ratingDto.getBookId())
                    .orElseThrow(() -> new RuntimeException("Book not found"));
            rating.setBookId(book);
        }

        return rating;


    }

    @Override
    public List<RatingDto> convertListOfRatingsToRatingDto(List<Rating> ratings) {
        List<RatingDto> ratingDtos = new ArrayList<>();
        for (Rating rating: ratings){
            ratingDtos.add(convertRatingToRatingDto(rating));
        }
        return ratingDtos;
    }
}
