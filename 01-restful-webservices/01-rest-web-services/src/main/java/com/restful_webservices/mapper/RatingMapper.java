package com.restful_webservices.mapper;

import com.restful_webservices.dto.RatingDto;
import com.restful_webservices.entity.Rating;

import java.util.List;

public interface RatingMapper {
    RatingDto convertRatingToRatingDto(Rating rating);
    Rating convertRatingDtoToRating(RatingDto ratingDto);
    List<RatingDto> convertListOfRatingsToRatingDto(List<Rating> ratings);
}
