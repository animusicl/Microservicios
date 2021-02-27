package com.academy.digitallab.store.product.repository;

import com.academy.digitallab.store.product.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CategoryRepository extends JpaRepository <Category, Long> {
}
