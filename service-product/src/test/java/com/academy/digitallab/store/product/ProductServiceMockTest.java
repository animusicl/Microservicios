package com.academy.digitallab.store.product;

import com.academy.digitallab.store.product.model.Category;
import com.academy.digitallab.store.product.model.Product;
import com.academy.digitallab.store.product.repository.ProductRepository;
import com.academy.digitallab.store.product.service.ProductService;
import com.academy.digitallab.store.product.service.ProductServiceImp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

@SpringBootTest
public class ProductServiceMockTest {

    @Mock
    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    public void setup() {

        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImp(productRepository);

        Product computer = Product
                .builder()
                .name("Computer")
                .category(Category.builder().id(1L).build())
                .stock(Double.parseDouble("24.1"))
                .price(Double.parseDouble("1245.66"))
                .status("Created")
                .build();

        Mockito.when(productRepository
                .findById(1L))
                .thenReturn(Optional.of(computer));
        Mockito.when(productRepository
                .save(computer))
                .thenReturn(computer);

    }

    @Test
    public void whenValidGetID_ThenReturnProduct(){
        Product found = productService.getProduct(1L);
        Assertions.assertThat(
                found.getName())
                .isEqualTo("Computer");
    }

    @Test
    public void whenValidUpdateStock_ThenReturnNewStock() {
        Product newStock = productService.updateStock(
                1L,
                Double.parseDouble("-3"));
        Assertions.assertThat(
                newStock.getStock())
                .isEqualTo(21.1);
    }

    @Test
    public void whenValidCreateProduct_ThenReturnNewProduct() {
        Product found = productService.getProduct(1L);

        Product newProduct = productService.createProduct(found);
        Assertions.assertThat(
                newProduct.getStatus())
                .isEqualTo("Created");
    }

    @Test
    public void whenValidDeleteProduct_ThenReturnNewStatusDeleted() {
        Product productToDelete = productService.deleteProduct(1L);
        Assertions.assertThat(
                productToDelete.getStatus())
                .isEqualTo("Deleted");
    }

}
