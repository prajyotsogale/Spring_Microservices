package com.restful_webservices.service;

import com.restful_webservices.dto.AuthorDto;
import com.restful_webservices.dto.BookDto;
import com.restful_webservices.entity.Author;
import com.restful_webservices.mapper.AuthorMapper;
import com.restful_webservices.mapper.BookMapper;
import com.restful_webservices.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService{

    private AuthorRepository authorRepository;
    private AuthorMapper authorMapper;
    private BookMapper bookMapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper,
                             BookMapper bookMapper){
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
        this.bookMapper = bookMapper;
    }

    @Override
    public AuthorDto addAuthor(AuthorDto authorDto) {
        Author author = authorMapper.convertDtoToAuthor(authorDto);
        return authorMapper.convertAuthorToDto(authorRepository.save(author));
    }

    @Override
    public List<AuthorDto> findAllAuthors() {
        return authorMapper.convertAuthorsToDto(authorRepository.findAll());
    }

    @Override
    public AuthorDto getAuthorById(int id) {
        Optional<Author> tempAuthor = authorRepository.findById(id);
        if(tempAuthor.isEmpty()){
            throw new RuntimeException("no Author found by id: "+id);
        }
        return authorMapper.convertAuthorToDto(tempAuthor.get());
    }

    @Override
    public List<BookDto> getBooksByAuthorId(int id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        return bookMapper.convertListOfBooksToDto(author.getBooks());
    }

    @Override
    public AuthorDto updateAuthor(AuthorDto author) {
        Author authorDb =  authorRepository.save(authorMapper.convertDtoToAuthor(author));
        return authorMapper.convertAuthorToDto(authorDb);
    }

    @Override
    public void deleteAuthorById(int id) {
        authorRepository.deleteById(id);
    }


}
