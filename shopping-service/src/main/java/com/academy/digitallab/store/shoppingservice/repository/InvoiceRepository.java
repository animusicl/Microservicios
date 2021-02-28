package com.academy.digitallab.store.shoppingservice.repository;

import com.academy.digitallab.store.shoppingservice.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository <Invoice, Long> {

    public List <Invoice> findByCustomerId(Long customerId);
    public Invoice findByInvoiceNumber (String invoiceNumber);

}
