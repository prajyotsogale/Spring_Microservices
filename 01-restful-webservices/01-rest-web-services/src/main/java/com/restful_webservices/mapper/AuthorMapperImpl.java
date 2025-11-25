package com.restful_webservices.mapper;

import com.restful_webservices.dto.AuthorDto;
import com.restful_webservices.entity.Author;
import com.restful_webservices.entity.Book;
import com.restful_webservices.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AuthorMapperImpl implements AuthorMapper{

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorMapperImpl(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorDto convertAuthorToDto(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setName(author.getName());
        authorDto.setBiography(author.getBiography());
        List<String> titles = new ArrayList<>();
        if(author.getBooks() != null){
            for (Book book : author.getBooks()){
                titles.add(book.getTitle());
            }
        }
        authorDto.setBookTitles(titles);
        return authorDto;
    }

    @Override
    public List<AuthorDto> convertAuthorsToDto(List<Author> authors) {
        List<AuthorDto> authorDtoList = new ArrayList<>();
        for (Author author: authors){
            authorDtoList.add(convertAuthorToDto(author));
        }
        return authorDtoList;
    }

    @Override
    public Author convertDtoToAuthor(AuthorDto authorDto) {
        Author author;

        if(authorDto.getId() != null){
            author = authorRepository.findById(authorDto.getId())
                    .orElseThrow(() -> new RuntimeException("Author not found"));
        }else{
            author = new Author();
        }
        author.setName(authorDto.getName());
        author.setBiography(authorDto.getBiography());
        if(authorDto.getBookTitles() != null){
            List<Book> bookList = new ArrayList<>();
            for (String bookTitles: authorDto.getBookTitles()){
                Book book = new Book();
                book.setTitle(bookTitles);
                book.setAuthor(author);
                bookList.add(book);
            }
            author.setBooks(bookList);
        }
        return author;
    }
}
