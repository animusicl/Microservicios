package com.academy.digitallab.store.customer.service;

import com.academy.digitallab.store.customer.entity.Customer;
import com.academy.digitallab.store.customer.entity.Region;
import com.academy.digitallab.store.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerServiceImp implements CustomerService {


    CustomerRepository customerRepository;
    public CustomerServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findCustomerByRegion(Region region) {
        return customerRepository.findByRegion(region);
    }

    @Override
    public Customer createCustomer(Customer customer) { //para que sea idempotente

        Customer customerDB = customerRepository.findByDni(customer.getDni());

        if(customerDB != null) { //controlamos si ya existe en la BD y devolvemos el mismo objeto.
            return customerDB;
        }
        customer.setState("CREATED"); //si no existe en la BD lo creamos
        customerDB = customerRepository.save(customer);
        return customerDB;
    }

    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer customerDB = getCustomer(customer.getId());

        if ( customerDB == null) {
            return null;
        }
        customerDB.setFistName(customer.getFistName());
        customerDB.setLastName(customer.getLastName());
        customerDB.setEmail(customer.getEmail());
        customerDB.setPhotoUrl(customer.getPhotoUrl());

        return customerRepository.save(customerDB);

    }

    @Override
    public Customer deleteCustomer(Customer customer) {
        Customer customerDB = getCustomer(customer.getId());
        if (customerDB == null ){
            return null;
        }
        customer.setState("DELETED");
        return customerRepository.save(customer);
    }
}
