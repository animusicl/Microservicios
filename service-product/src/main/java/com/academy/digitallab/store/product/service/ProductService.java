package com.academy.digitallab.store.product.service;

import com.academy.digitallab.store.product.model.Category;
import com.academy.digitallab.store.product.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> listAllProduct();
    Product getProduct(Long id);
    Product createProduct(Product product);
    Product updateProduct(Product product);
    Product deleteProduct(Long id);
    List<Product> findByCategory(Category category);
    Product updateStock(Long id, Double quantity);


}
