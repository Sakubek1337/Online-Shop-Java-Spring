package com.example.demo.Repos;

import com.example.demo.Entities.Quote;
import com.example.demo.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM users WHERE email=:email", nativeQuery = true)
    List<User> findUser(@Param("email") String email);
}
