package com.restful_webservices.mapper;

import com.restful_webservices.dto.BookDto;
import com.restful_webservices.entity.Author;
import com.restful_webservices.entity.Book;
import com.restful_webservices.entity.Category;
import com.restful_webservices.entity.Rating;
import com.restful_webservices.repository.AuthorRepository;
import com.restful_webservices.repository.BookRepository;
import com.restful_webservices.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Configuration
public class BookMapperImpl implements BookMapper{

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public BookMapperImpl(BookRepository bookRepository, AuthorRepository authorRepository,
                          CategoryRepository categoryRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public BookDto convertBookToDto(Book book) {
        BookDto bookDto =  new BookDto();
        bookDto.setId(book.getId());
        bookDto.setPrice(book.getPrice());
        bookDto.setPublishedYear(book.getPublishedYear());
        bookDto.setStock(book.getStock());
        bookDto.setTitle(book.getTitle());
        if(book.getAuthor() != null){
            bookDto.setAuthorName(book.getAuthor().getName());
        }


        if(book.getCategory() != null){
            bookDto.setCategoryName(book.getCategory().getCategoryName());
        }else{
            bookDto.setCategoryName(null);
        }



        bookDto.setDescription(book.getDescription());
        //bookDto.setRatings(book.getRatings().);
        if(book.getRatings() != null){
            List<Integer> ratings = new ArrayList<>();
            for (Rating rating: book.getRatings()){
                ratings.add(rating.getRating());
            }
            bookDto.setRatings(ratings);
        }

        return bookDto;
    }

    @Override
    public Book convertBookDtoToBook(BookDto dto) {
        Book book;

        // If ID exists → update
        if (dto.getId() != null) {
            book = bookRepository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Book not found"));
        } else {
            // Create new book
            book = new Book();
        }

        // Basic fields
        book.setTitle(dto.getTitle());
        book.setPrice(dto.getPrice());
        book.setStock(dto.getStock());
        book.setPublishedYear(dto.getPublishedYear());
        book.setDescription(dto.getDescription());

//        // Author
        if (dto.getAuthorName() != null) {
            Optional<Author> OptionalAuthor = authorRepository.findByName(dto.getAuthorName());
            if (OptionalAuthor.isEmpty()) {
                //throw new RuntimeException("Author not found");
                //not throwing the exception but creating the author
                Author newAuthor = new Author();
                newAuthor.setName(dto.getAuthorName());
                book.setAuthor(newAuthor);
//                authorRepository.save(newAuthor);
            }else{
                book.setAuthor(OptionalAuthor.get());
            }

        }

        // Category
        if (dto.getCategoryName() != null) {
            Category category = categoryRepository.findByCategoryName(dto.getCategoryName());
            if (category == null) {
                //throw new RuntimeException("Category not found");
                //creating the new category
                category = new Category();
                category.setCategoryName(dto.getCategoryName());
                category.setBookList(Arrays.asList(book));
//                categoryRepository.save(category);

            }
            book.setCategory(category);


        }

        // Ratings
        if (dto.getRatings() != null) {
            List<Rating> ratingList = new ArrayList<>();

            for (Integer ratingValue : dto.getRatings()) {
                Rating rating = new Rating();
                rating.setRating(ratingValue);
                rating.setBookId(book); // maintain relationship
                ratingList.add(rating);
            }

            book.setRatings(ratingList);
        }

        return book;

    }

    @Override
    public List<BookDto> convertListOfBooksToDto(List<Book> books) {
        List<BookDto> booksDto = new ArrayList<>();
        for (Book book: books){
            booksDto.add(convertBookToDto(book));
        }

        return booksDto;
    }
}
