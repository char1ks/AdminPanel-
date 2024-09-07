package com.example.CookiesPractice.repository;

import com.example.CookiesPractice.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface productRepository extends JpaRepository<Product, Integer> {
    List<Product> findByPersonIdIsNull();
    Page<Product> findAll(Pageable pageable);
    List<Product> findAll(Sort var1);
}