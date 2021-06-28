package com.academy.digitallab.store.shoppingservice.client;

import com.academy.digitallab.store.shoppingservice.model.Customer;
import com.academy.digitallab.store.shoppingservice.model.Region;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerHystrixFallbackFactory implements CustomerClient {

    @Override
    public ResponseEntity<Customer> getCustomer(Long id) {
        Customer customer = Customer.builder()
                .dni("XXXXXXXX")
                .fistName("what")
                .lastName("ever")
                .email("whatever@gmail.com")
                .photoUrl("someurl")
                .region(new Region())
                .state("created")
                .build();

        return ResponseEntity.ok(customer);
    }
}
