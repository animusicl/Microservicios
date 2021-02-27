package com.academy.digitallab.store.product.repository;

import com.academy.digitallab.store.product.model.Category;
import com.academy.digitallab.store.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {

    public List<Product> findByCategory(Category category);
}
