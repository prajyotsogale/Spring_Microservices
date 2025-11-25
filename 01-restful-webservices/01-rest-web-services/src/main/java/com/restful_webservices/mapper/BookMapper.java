package com.restful_webservices.mapper;

import com.restful_webservices.dto.BookDto;
import com.restful_webservices.entity.Book;

import java.util.List;

public interface BookMapper {
    BookDto convertBookToDto(Book book);
    Book convertBookDtoToBook(BookDto bookDto);
    List<BookDto> convertListOfBooksToDto(List<Book> books);
}
