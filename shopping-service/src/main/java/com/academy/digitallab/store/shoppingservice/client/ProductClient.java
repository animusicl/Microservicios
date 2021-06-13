package com.academy.digitallab.store.shoppingservice.client;

import com.academy.digitallab.store.shoppingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "product-service")
@RequestMapping(path = "api/v1/product")
public interface ProductClient {

    @GetMapping("/{id}")
    ResponseEntity<Product> getProduct(@RequestParam(name = "id") Long id);

    @PatchMapping("/{id}/stock")
    ResponseEntity <Product> updateSock (
            @PathVariable("id") Long id,
            @RequestParam (name = "quantity", required = true) Double quantity);
}
