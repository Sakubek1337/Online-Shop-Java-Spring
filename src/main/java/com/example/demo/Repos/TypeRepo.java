package com.example.demo.Repos;

import com.example.demo.Models.Entities.TypeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepo extends JpaRepository<TypeInfo, Integer> {
}
