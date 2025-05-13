package com.example.javatutorial.Repository;

import com.example.javatutorial.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
