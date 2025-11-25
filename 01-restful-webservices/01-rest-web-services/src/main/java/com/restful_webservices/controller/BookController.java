package com.restful_webservices.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.restful_webservices.dto.BookDto;
import com.restful_webservices.entity.Author;
import com.restful_webservices.entity.Book;
import com.restful_webservices.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
public class BookController {
    private BookService bookService;
    private ObjectMapper objectMapper;

    @Autowired
    public BookController(BookService bookService, ObjectMapper objectMapper){
        this.bookService = bookService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/books")
    public List<BookDto> getBooks(){
        return bookService.findAll();
    }
    @GetMapping("/books/{id}")
    public BookDto getBook(@PathVariable int id){
        return bookService.findBookById(id);
    }

    @PostMapping("/books")
    public BookDto createBook(@RequestBody BookDto book){
        return bookService.save(book);
    }
    @PutMapping("/books/{id}")
    public BookDto updateBook(@PathVariable int id, @RequestBody BookDto updatedBook){
        updatedBook.setId(id);
        return bookService.updateBook(updatedBook);
    }
    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable int id){
        bookService.deleteBookById(id);
    }
    //patch
    @PatchMapping("/books/{id}")
    public BookDto partialUpdateBook(@PathVariable int id, @RequestBody Map<String, Object> patchPayload){
        BookDto bookDto = bookService.findBookById(id);
        if(patchPayload.containsKey("id")){
            throw new RuntimeException("don't write id in it");
        }
        BookDto patchedBook = apply(patchPayload, bookDto);
        return bookService.updateBook(patchedBook);
    }
    public BookDto apply(Map<String, Object> patchPayload, BookDto bookDto){
        //convert both to object node
        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);
        ObjectNode bookNode = objectMapper.convertValue(bookDto, ObjectNode.class);

        //merge
        bookNode.setAll(patchNode);
        //convert to returning type
        return objectMapper.convertValue(bookNode, BookDto.class);
    }

    @GetMapping("/books/titles/{title}")
    public BookDto getBookByTitle(@PathVariable String title){
        return bookService.searchByTitle(title);
    }

    @GetMapping("/books/search-by-published-year")
    public List<BookDto> searchByPublishedDate(@RequestParam LocalDate date){
        return bookService.searchByPublishedDate(date);
    }

    @GetMapping("/books/filter-by-rating")
    public List<BookDto> filterBooksByRating(@RequestParam int rating){
        return bookService.filterBooksByRating(rating);
    }

    @GetMapping("/books/available-stocks")
    public List<BookDto> filterByAvailableStocks(){
        return bookService.filterByAvailableStocks();
    }



}
