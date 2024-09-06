package com.example.fakestoreapi.repository;

import com.example.fakestoreapi.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
