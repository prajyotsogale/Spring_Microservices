package com.restful_webservices.mapper;

import com.restful_webservices.dto.AuthorDto;
import com.restful_webservices.entity.Author;

import java.util.List;

public interface AuthorMapper {
    AuthorDto convertAuthorToDto(Author author);
    List<AuthorDto> convertAuthorsToDto(List<Author> authors);
    Author convertDtoToAuthor(AuthorDto authorDto);
}
