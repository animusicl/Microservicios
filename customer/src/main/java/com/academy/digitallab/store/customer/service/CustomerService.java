package com.academy.digitallab.store.customer.service;

import com.academy.digitallab.store.customer.entity.Customer;
import com.academy.digitallab.store.customer.entity.Region;

import java.util.List;

public interface CustomerService {

    public List <Customer> findAllCustomers();
    public List <Customer> findCustomerByRegion(Region region);

    public Customer createCustomer(Customer customer);
    public Customer getCustomer(Long id);
    public Customer updateCustomer(Customer customer);
    public Customer deleteCustomer(Customer customer);


}
