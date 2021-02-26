package com.academy.digitallab.store.product.controller;

import com.academy.digitallab.store.product.model.Category;
import com.academy.digitallab.store.product.model.Product;
import com.academy.digitallab.store.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import javax.validation.Valid;
import java.util.List;




@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity <List<Product>> getAllProducts (
            @RequestParam (
                    name = "categoryId",
                    required = false)
                    Long category_id) {

        List <Product> products;
        if (null == category_id) {

            return ResponseEntity.ok(productService.listAllProduct());
        } else {

            products = productService.findByCategory(Category
                    .builder()
                    .id(category_id)
                    .build());

            if (products.isEmpty()){

                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(products);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity <Product> getProduct(@RequestParam(name = "id") Long id) {
        Product p = productService.getProduct(id);
        return (p == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(p);
    }

    @PostMapping
    public ResponseEntity <Product> createProduct ( //I need to handle validation
            @Valid
            @RequestBody Product newProduct,
            BindingResult result) {

        if (result.hasErrors()) {

            throw new ResponseStatusException (
                    HttpStatus.BAD_REQUEST,
                    "Some information is not valid");
        }

        return ResponseEntity.ok(productService.createProduct(newProduct));
    }

    @PutMapping("/{id}")
    public ResponseEntity <Product> updateProduct (
            @PathVariable("id") Long id,
            @RequestBody Product product) {

        product.setId(id);
        Product productDB = productService.updateProduct(product);

        return (productDB == null) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(productDB);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Product> deleteProduct (@PathVariable Long id) {

        Product productToDelete = productService.deleteProduct(id);

        return (productToDelete == null ) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(productToDelete);
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity <Product> updateSock (
            @PathVariable ("id") Long id,
            @RequestParam (name = "quantity", required = true) Double quantity) {
        Product product = productService.updateStock (id, quantity);

        return (product == null) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(product);
    }


}

