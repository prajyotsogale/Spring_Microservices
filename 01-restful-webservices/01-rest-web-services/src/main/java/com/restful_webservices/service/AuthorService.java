package com.restful_webservices.service;


import com.restful_webservices.dto.AuthorDto;
import com.restful_webservices.dto.BookDto;
import com.restful_webservices.entity.Author;
import com.restful_webservices.entity.Book;

import java.util.List;

public interface AuthorService {
    AuthorDto addAuthor(AuthorDto authorDto);
    List<AuthorDto> findAllAuthors();
    AuthorDto getAuthorById(int id);
    List<BookDto> getBooksByAuthorId(int id);
    AuthorDto updateAuthor(AuthorDto author);
    void deleteAuthorById(int id);
}
