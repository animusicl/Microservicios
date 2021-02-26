package com.academy.digitallab.store.product;

import com.academy.digitallab.store.product.model.Category;
import com.academy.digitallab.store.product.model.Product;
import com.academy.digitallab.store.product.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Date;
import java.util.List;

@DataJpaTest
public class ProductRepositoryMockTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void whenFindByCategoryReturnListProduct(){
        Product product01 = Product
                .builder()
                .name("Computer")
                .category(Category.builder().id(1L).build())
                .description("I'm a description in a test object")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1245.66"))
                .status("Created")
                .createdAt(new Date())
                .build();

        productRepository.save(product01);

        List<Product> found = productRepository
                .findByCategory(product01
                        .getCategory());

        Assertions
                .assertThat(found.size())
                .isEqualTo(3);

    }
}
