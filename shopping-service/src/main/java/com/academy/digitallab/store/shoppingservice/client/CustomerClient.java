package com.academy.digitallab.store.shoppingservice.client;

import com.academy.digitallab.store.shoppingservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "customer-service")
@RequestMapping(path = "api/v1/customer")
public interface CustomerClient {

    @GetMapping("/{id}")
    public ResponseEntity <Customer> getCustomer (@PathVariable("id") Long id);
}
