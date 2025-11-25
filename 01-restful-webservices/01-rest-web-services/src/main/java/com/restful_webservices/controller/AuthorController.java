package com.restful_webservices.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.restful_webservices.dto.AuthorDto;
import com.restful_webservices.dto.BookDto;
import com.restful_webservices.entity.Author;
import com.restful_webservices.entity.Book;
import com.restful_webservices.mapper.AuthorMapper;
import com.restful_webservices.service.AuthorService;
import com.restful_webservices.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class AuthorController {

    private AuthorService authorService;
    private AuthorMapper authorMapper;
    private ObjectMapper objectMapper;

    @Autowired
    public AuthorController(AuthorService authorService, ObjectMapper objectMapper){
        this.authorService = authorService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/authors")
    public List<AuthorDto> getAuthors(){
        return  authorService.findAllAuthors();
    }
    @GetMapping("/authors/{id}")
    public AuthorDto getAuthor(@PathVariable int id){
        return authorService.getAuthorById(id);
    }
    @GetMapping("/authors/{id}/books")
    public List<BookDto> getBooksByAuthorId(@PathVariable int id){
        return authorService.getBooksByAuthorId(id);
    }
    @PostMapping("/authors")
    public AuthorDto createAuthor(@RequestBody AuthorDto authorDto){
        return authorService.addAuthor(authorDto);
    }
    @PutMapping("/authors/{id}")
    public AuthorDto updateAuthor(@PathVariable int id, @RequestBody AuthorDto updatedAuthor){
        updatedAuthor.setId(id);
        return authorService.updateAuthor(updatedAuthor);
    }
    @PatchMapping("/authors/{id}")
    public AuthorDto partialUpdateAuthor(@PathVariable int id, @RequestBody Map<String, Object> patchPayload){
        AuthorDto author = authorService.getAuthorById(id);

        if(patchPayload.containsKey("id")){
            throw new RuntimeException("don't write id in it");
        }
        AuthorDto patchedAuthor = apply(patchPayload, author);
        return authorService.updateAuthor(patchedAuthor);
    }
    @DeleteMapping("/authors/{id}")
    public void deleteAuthor(@PathVariable int id){
        authorService.deleteAuthorById(id);
    }
    //get post put patch delete
    public AuthorDto apply(Map<String, Object> patchPayload, AuthorDto author){
        //convert employee object to a json object node
        ObjectNode authorNode = objectMapper.convertValue(author, ObjectNode.class);
        //convert patch payload map to a json object node
        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);
        //merge the patch updates into employee node
        authorNode.setAll(patchNode);
        //convert it back and return
        return objectMapper.convertValue(authorNode, AuthorDto.class);
    }

}
