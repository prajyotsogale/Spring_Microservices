package com.restful_webservices.service;

import com.restful_webservices.dto.BookDto;
import com.restful_webservices.entity.Author;
import com.restful_webservices.entity.Book;
import com.restful_webservices.entity.Rating;
import com.restful_webservices.mapper.BookMapper;
import com.restful_webservices.repository.AuthorRepository;
import com.restful_webservices.repository.BookRepository;
import com.restful_webservices.repository.CategoryRepository;
import com.restful_webservices.repository.RatingRepository;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;
    private RatingService ratingService;
    private AuthorRepository authorRepository;
    private CategoryRepository categoryRepository;
    private BookMapper bookMapper;
    private RatingRepository ratingRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, RatingService ratingService,
                           BookMapper bookMapper, AuthorRepository authorRepository,
                           CategoryRepository categoryRepository, RatingRepository ratingRepository){
        this.ratingRepository = ratingRepository;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.ratingService = ratingService;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDto save(BookDto bookDto) {
        Book book = bookMapper.convertBookDtoToBook(bookDto);
        authorRepository.save(book.getAuthor());
        categoryRepository.save(book.getCategory());
        ratingRepository.saveAll(book.getRatings());
        return bookMapper.convertBookToDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAll() {
        return bookMapper.convertListOfBooksToDto(bookRepository.findAll());
    }

    @Override
    public BookDto findBookById(int id) {
        Optional<Book> tempBook = bookRepository.findById(id);

        if(tempBook.isEmpty()){
            throw new RuntimeException("can't find book by id: "+id);
        }

        return bookMapper.convertBookToDto(tempBook.get());
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        return save(bookDto);
    }

    @Override
    public void deleteBookById(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookDto searchByTitle(String title) {
        Book book = bookRepository.findByTitleIgnoreCase(title);
        if(book == null){
            throw new RuntimeException("can't find book with title: "+title);
        }
        return bookMapper.convertBookToDto(book);
    }


    @Override
    public List<BookDto> searchByPublishedDate(LocalDate date) {
        return bookMapper.convertListOfBooksToDto(bookRepository.findByPublishedYear(date));
    }

    @Override
    public List<BookDto> filterBooksByRating(int rating) {

        List<Book> allBooks = bookRepository.findAll();
        List<Book> finalBookList = new ArrayList<>();

        for (Book book: allBooks){
            if(ratingService.getAverageRating(book) >= rating){
                finalBookList.add(book);
            }
        }

        return bookMapper.convertListOfBooksToDto(finalBookList);

    }

    @Override
    public List<BookDto> filterByAvailableStocks() {
        return bookMapper.convertListOfBooksToDto(bookRepository.findByStockGreaterThan(0));
    }

}
