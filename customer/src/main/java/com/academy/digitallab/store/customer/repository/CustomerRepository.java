package com.academy.digitallab.store.customer.repository;

import com.academy.digitallab.store.customer.entity.Customer;
import com.academy.digitallab.store.customer.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CustomerRepository extends JpaRepository <Customer, Long> {

    public Customer findByDni(String dni);
    public List<Customer> findByLastName(String lastName);
    public List <Customer> findByRegion(Region region);
}
