package com.restful_webservices.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.restful_webservices.dto.BookDto;
import com.restful_webservices.dto.RatingDto;
import com.restful_webservices.entity.Book;
import com.restful_webservices.entity.Rating;
import com.restful_webservices.service.BookService;
import com.restful_webservices.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class RatingController {
    private RatingService ratingService;
    private ObjectMapper objectMapper;
    private BookService bookService;

    @Autowired
    public RatingController(RatingService ratingService, ObjectMapper objectMapper,
                            BookService bookService){
        this.ratingService = ratingService;
        this.bookService = bookService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/ratings/{id}")
    public RatingDto getRatingById(@PathVariable int id){
        return ratingService.getRatingById(id);
    }

    @GetMapping("/books/{id}/ratings")
    public List<RatingDto> getRatings(@PathVariable int id){
        return ratingService.findAllRatingsByBookId(id);
    }
    @GetMapping("/books/{bookId}/ratings/{ratingId}")
    public RatingDto getRating(@PathVariable int bookId, @PathVariable int ratingId){
        return ratingService.getRatingById(ratingId);
    }

    @PostMapping("/books/{bookId}/ratings")
    public RatingDto createRating(@PathVariable int bookId , @RequestBody RatingDto rating){
        return ratingService.addRatingToBook(rating, bookId);
    }
    @PutMapping("/ratings/{id}")
    public RatingDto updateRating(@PathVariable int id, @RequestBody RatingDto updatedRating){
        updatedRating.setId(id);
        return ratingService.updateRating(updatedRating);
    }
    @DeleteMapping("/ratings/{id}")
    public void deleteRating(@PathVariable int id){
        ratingService.deleteRatingById(id);
    }
    //patch
    @PatchMapping("/ratings/{id}")
    public RatingDto partialUpdateCategory(@PathVariable int id, @RequestBody Map<String, Object> patchPayload){
        RatingDto ratingDto = ratingService.getRatingById(id);
        if(patchPayload.containsKey("id")){
            throw new RuntimeException("don't write id in it");
        }
        RatingDto patchedRating = apply(patchPayload, ratingDto);
        return ratingService.updateRating(patchedRating);
    }
    public RatingDto apply(Map<String, Object> patchPayload, RatingDto ratingDto){
        //convert both to object node
        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);
        ObjectNode ratingNode = objectMapper.convertValue(ratingDto, ObjectNode.class);

        //merge
        ratingNode.setAll(patchNode);
        //convert to returning type
        return objectMapper.convertValue(ratingNode, RatingDto.class);
    }

    @GetMapping("/books/sorted/average-rating")
    public List<BookDto> sortByRating(){
        return ratingService.sortBooksByRating(bookService.findAll());
    }
}
