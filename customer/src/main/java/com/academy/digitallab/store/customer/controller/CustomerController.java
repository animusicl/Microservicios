package com.academy.digitallab.store.customer.controller;

import com.academy.digitallab.store.customer.entity.Customer;
import com.academy.digitallab.store.customer.entity.Region;
import com.academy.digitallab.store.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "api/v1/customer")
public class CustomerController {

    private CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(
            @RequestParam(name = "region", required = false) Long region_id){

        List<Customer> customers;

        if (region_id == null) {

            customers = customerService.findAllCustomers();

        } else {

            Region region = new Region();
            region.setId(region_id);
            customers = customerService.findCustomerByRegion(region);
            if (customers == null){
                log.error("Customer with Region id {} not found.",region_id);
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
        log.info("Fetching customer with id {} ",id);
        Customer customer = customerService.getCustomer(id);
        if (customer == null){
            log.error("Customer with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer ( // I need to find a way to handle errorFields.
            @Valid @RequestBody Customer customer,
            BindingResult result) {
        log.info("Creating customer: {}", customer);
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "something got wrong");
        }                                       // I need to find a way to handle errorFields.
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerService
                        .createCustomer(customer));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer (
            @PathVariable("id") Long id,
            @RequestBody Customer customer) {
        log.info("Updating customer with id {} ", id);

        Customer customerToUpdate = customerService.getCustomer(id);
        if (customerToUpdate == null) {
            log.error("Unable to update. Customer with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }

        customer.setId(id);
        customerToUpdate = customerService.updateCustomer(customer);
        return ResponseEntity.ok(customerToUpdate);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Long id) {
        log.info("Fetching and Deleting customer with id {}. ", id);

        Customer customerToDelete = customerService.getCustomer(id);
        if (customerToDelete == null) {
            log.error("Unable to delete. Customer with id {} not found.");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerService.deleteCustomer(customerToDelete));
    }






}
