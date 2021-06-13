package com.academy.digitallab.store.product.service;

import com.academy.digitallab.store.product.model.Category;
import com.academy.digitallab.store.product.model.Product;
import com.academy.digitallab.store.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> listAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) { //tested
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) { //tested
        product.setStatus("Created");
        product.setCreatedAt(new Date());
        return productRepository.save(product);

    }

    @Override
    public Product updateProduct(Product product) { //tested
        Product productDB = getProduct(product.getId());

        if (productDB == null){
            return null;
        }
        productDB.setName(product.getName());
        productDB.setDescription(product.getDescription());
        productDB.setStock(product.getStock());
        productDB.setPrice(product.getPrice());
        productDB.setCategory(product.getCategory());

        return productRepository.save(productDB);
    }

    @Override
    public Product deleteProduct(Long id) { //tested

        Product productDB = getProduct(id);
        if (null == productDB){
            return null;
        }
        productDB.setStatus("Deleted");
        return productRepository.save(productDB);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product updateStock(Long id, Double quantity) {

        Product productDB = getProduct(id);
        if (null == productDB){
            return null;
        }
        productDB.setStock(productDB.getStock()+ quantity);

        return productRepository.save(productDB);
    }
}
