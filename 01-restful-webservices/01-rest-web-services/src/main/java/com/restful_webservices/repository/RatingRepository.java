package com.restful_webservices.repository;

import com.restful_webservices.entity.Book;
import com.restful_webservices.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    List<Rating> findByBookId_Id(int id);
}
