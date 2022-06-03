package com.example.demo.Repos;

import com.example.demo.Models.Entities.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuotesRepo extends JpaRepository<Quote, Integer> {
    @Query(value = "SELECT * FROM quotes WHERE type=:type", nativeQuery = true)
    List<Quote> findByType(@Param("type") String type);

    @Query(value = "SELECT DISTINCT ON(type) type, id, text, author FROM quotes", nativeQuery = true)
    List<Quote> getTypes();
}