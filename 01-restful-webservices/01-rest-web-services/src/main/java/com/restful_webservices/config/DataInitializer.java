package com.restful_webservices.config;

import com.restful_webservices.entity.Author;
import com.restful_webservices.entity.Book;
import com.restful_webservices.entity.Category;
import com.restful_webservices.entity.Rating;
import com.restful_webservices.repository.AuthorRepository;
import com.restful_webservices.repository.BookRepository;
import com.restful_webservices.repository.CategoryRepository;
import com.restful_webservices.repository.RatingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DataInitializer {
    //global variable for books

    Book book1 = new Book("The Alchemist", "A young shepherd travels from Spain to the Egyptian desert in search of treasure.", 1345, LocalDate.of(1988, 1, 1), 150);
    Book book2 = new Book("Veronika Decides to Die", "A young Slovenian woman decides to take her own life, only to wake up in a mental institution.", 1076, LocalDate.of(1998, 1, 1), 85);
    Book book3 = new Book("Eleven Minutes", "A young Brazilian woman travels to Geneva hoping to find a better life as a prostitute.", 1255, LocalDate.of(2003, 1, 1), 75);
    Book book4 = new Book("The Pilgrimage", "Based on Coelho's own experience traversing the Road to Santiago.", 986, LocalDate.of(1987, 1, 1), 60);
    Book book5 = new Book("Brida", "A young Irish girl explores different magical traditions on her quest to become a witch.", 1165, LocalDate.of(1990, 1, 1), 90);
    Book book6 = new Book("The Devil and Miss Prym", "A town is offered vast wealth if they commit a murder.", 1076, LocalDate.of(2000, 1, 1), 70);
    Book book7 = new Book("The Zahir", "A best-selling author's wife disappears without a trace, sending him on a global search.", 1434, LocalDate.of(2005, 1, 1), 110);
    Book book8 = new Book("Hippie", "A fictionalized account of his 1970 journey across Europe to Kathmandu.", 1255, LocalDate.of(2018, 1, 1), 55);
    Book book9 = new Book("Harry Potter and the Sorcerer's Stone", "An orphaned boy discovers he is a wizard and begins his education at Hogwarts School of Witchcraft and Wizardry.", 450, LocalDate.of(1997, 6, 26), 300);
    Book book10 = new Book("Harry Potter and the Chamber of Secrets", "Harry returns for his second year, facing a dark force terrorizing the students of Hogwarts.", 450, LocalDate.of(1998, 7, 2), 250);
    Book book11 = new Book("Norwegian Wood", "A poignant story of loss and sexuality told from the perspective of a man recalling his college days in Tokyo.", 399, LocalDate.of(1987, 9, 4), 120);
    Book book12 = new Book("Kafka on the Shore", "A two-part narrative following a runaway teenage boy and an aging man who can talk to cats.", 499, LocalDate.of(2002, 9, 12), 115);
    Book book13 = new Book("Pride and Prejudice", "The story of Elizabeth Bennet and her four sisters as they navigate issues of marriage, class, and morality in 19th-century England.", 299, LocalDate.of(1813, 1, 28), 180);
    Book book14 = new Book("Sense and Sensibility", "Follows the Dashwood sisters, one who represents sense and the other sensibility, as they experience love, romance, and heartbreak.", 299, LocalDate.of(1811, 10, 30), 160);
    Book book15 = new Book("The Shining", "A family is terrorized by an evil presence in an isolated historic hotel during the off-season.", 550, LocalDate.of(1977, 1, 28), 95);
    Book book16 = new Book("It", "Seven outcast children in Derry, Maine, band together to fight a malevolent entity that takes the shape of a clown.", 699, LocalDate.of(1986, 9, 15), 130);


    //global list of books for categories
    List<Book> fictionBooks = Arrays.asList(
            book1, book2, book3, book4, book5, book6, book7, book8,
            book9, book10,
            book11, book12,
            book13, book14,
            book15, book16
    );


    List<Book> philosophicalFictionBooks = Arrays.asList(
            book1,
            book2,
            book4,
            book7
    );


    List<Book> fantasyBooks = Arrays.asList(
            book9,
            book10,
            book5
    );

    List<Book> biographyBooks = Arrays.asList(
            book8,
            book4
    );


    //categories
    Category category1 = new Category("Fiction",
            "Stories created from imagination, often exploring themes of human nature and society.",
            fictionBooks
    );

    Category category2 = new Category("Biography",
            "Accounts of someone's life written by another person.",
            biographyBooks
    );

    Category category3 = new Category("Philosophical Fiction",
            "Fictional narratives used to discuss and explore serious philosophical ideas and questions.",
            philosophicalFictionBooks
    );

    Category category4 = new Category("Fantasy",
            "Stories set in imaginary universes, often involving magic, mythical creatures, and supernatural events.",
            fantasyBooks
    );



    //global authors
    Author author1 = new Author("Paulo Coelho",
            "Brazilian novelist, lyricist, and member of the Brazilian Academy of Letters. He is best known for his allegorical and mystical novels, especially 'The Alchemist', which has sold over 150 million copies worldwide.");
    Author author2 = new Author("J.K. Rowling",
            "British novelist and philanthropist, best known for the Harry Potter fantasy series. Her work is celebrated for its intricate world-building, compelling characters, and themes of friendship and courage.");
    Author author3 = new Author("Haruki Murakami",
            "Japanese writer whose novels blend elements of magical realism, melancholy, and popular culture. His works, such as 'The Wind-Up Bird Chronicle' and 'Kafka on the Shore', explore loneliness and postmodern society.");
    Author author4 = new Author("Jane Austen",
            "English novelist whose works of romantic fiction, including 'Pride and Prejudice' and 'Sense and Sensibility', are set among the British landed gentry and are renowned for their social commentary and sharp wit.");


    @Bean
    public CommandLineRunner commandLineRunner(CategoryRepository categoryRepository, AuthorRepository authorRepository,
                                               BookRepository bookRepository, RatingRepository ratingRepository){
        return args ->{

            saveAndCreateAuthor(authorRepository);
            createAndAddReviews(ratingRepository);
            createAndAddToCategory(categoryRepository);
            setCategoryToBooks(bookRepository);
            setAuthorToBooks(bookRepository);

        };

    }

    private void setAuthorToBooks(BookRepository bookRepository) {
        book1.setAuthor(author1);
        book2.setAuthor(author1);
        book3.setAuthor(author1);
        book4.setAuthor(author1);
        book5.setAuthor(author1);
        book6.setAuthor(author1);
        book7.setAuthor(author1);
        book8.setAuthor(author1);
        book9.setAuthor(author2);
        book10.setAuthor(author2);
        book11.setAuthor(author3);
        book12.setAuthor(author3);
        book13.setAuthor(author4);
        book14.setAuthor(author4);
        book15.setAuthor(author2);
        book16.setAuthor(author3);
        bookRepository.saveAll(Arrays.asList(
                book1, book2, book3, book4, book5, book6, book7, book8,
                book9, book10, book11, book12, book13, book14, book15, book16
        ));
    }

    private void setCategoryToBooks(BookRepository bookRepository) {
        book1.setCategory(category3);
        book2.setCategory(category3);
        book3.setCategory(category1);
        book4.setCategory(category3);
        book5.setCategory(category4);
        book6.setCategory(category3);
        book7.setCategory(category3);
        book8.setCategory(category2);
        book9.setCategory(category4);
        book10.setCategory(category4);
        book11.setCategory(category1);
        book12.setCategory(category1);
        book13.setCategory(category1);
        book14.setCategory(category1);
        book15.setCategory(category1);
        book16.setCategory(category1);
        bookRepository.saveAll(Arrays.asList(
                book1, book2, book3, book4, book5, book6, book7, book8,
                book9, book10, book11, book12, book13, book14, book15, book16
        ));
    }

    private void saveAndCreateAuthor(AuthorRepository authorRepository) {
        authorRepository.saveAll(Arrays.asList(author1, author2, author3, author4));
    }

    private void createAndAddReviews(RatingRepository ratingRepository) {
        Rating rating1 = new Rating(5, "A truly inspiring journey of following your dreams and destiny.", LocalDate.of(2025, 10, 1), book1); // The Alchemist
        Rating rating2 = new Rating(4, "A deeply philosophical read about mental health and living authentically.", LocalDate.of(2025, 10, 5), book2); // Veronika Decides to Die
        Rating rating3 = new Rating(3, "The subject matter was heavy, but the ending offered a meaningful perspective.", LocalDate.of(2025, 10, 10), book3); // Eleven Minutes
        Rating rating4 = new Rating(5, "A beautiful, spiritual travelogue. Perfect for those seeking inner peace.", LocalDate.of(2025, 10, 15), book4); // The Pilgrimage
        Rating rating5 = new Rating(4, "Fascinating blend of Irish mythology and spiritual quest themes.", LocalDate.of(2025, 10, 20), book5); // Brida
        Rating rating6 = new Rating(2, "Did not connect with the allegory of good and evil as much as his other works.", LocalDate.of(2025, 10, 25), book6); // The Devil and Miss Prym
        Rating rating7 = new Rating(5, "A compelling, obsessive search for meaning after loss. Highly recommended.", LocalDate.of(2025, 10, 28), book7); // The Zahir
        Rating rating8 = new Rating(3, "A reflective, autobiographical novel. A bit slow at times but worth the read.", LocalDate.of(2025, 11, 2), book8); // Hippie

        Rating rating9 = new Rating(5, "A timeless classic that ignited a generation's love for reading.", LocalDate.of(2025, 11, 5), book9); // Sorcerer's Stone
        Rating rating10 = new Rating(5, "Excellent sequel! The mystery and dark elements were perfectly balanced.", LocalDate.of(2025, 11, 7), book10); // Chamber of Secrets
        Rating rating11 = new Rating(4, "A melancholic and beautifully written exploration of loneliness and memory.", LocalDate.of(2025, 11, 10), book11); // Norwegian Wood
        Rating rating12 = new Rating(4, "A bizarre, ambitious, and imaginative work of magical realism.", LocalDate.of(2025, 11, 12), book12); // Kafka on the Shore

        Rating rating13 = new Rating(5, "A masterpiece of social observation and witty dialogue. A must-read classic.", LocalDate.of(2025, 11, 15), book13); // Pride and Prejudice
        Rating rating14 = new Rating(3, "The societal constraints of the time made the romance feel frustratingly slow.", LocalDate.of(2025, 11, 18), book14); // Sense and Sensibility
        Rating rating15 = new Rating(5, "Terrifying psychological horror. A true masterclass in building dread.", LocalDate.of(2025, 11, 20), book15); // The Shining
        Rating rating16 = new Rating(5, "A true masterclass in building dread.", LocalDate.of(2025, 11, 20), book16); // The Shining

        ratingRepository.saveAll(Arrays.asList(
                rating1, rating2, rating3, rating4, rating5, rating6, rating7, rating8,
                rating9, rating10, rating11, rating12, rating13, rating14, rating15,rating16
        ));
    }

    private void createAndAddToCategory(CategoryRepository categoryRepository) {


        categoryRepository.saveAll(Arrays.asList(category1, category2, category3, category4));
    }

}
