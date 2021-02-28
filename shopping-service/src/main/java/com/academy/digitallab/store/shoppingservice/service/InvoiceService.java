package com.academy.digitallab.store.shoppingservice.service;

import com.academy.digitallab.store.shoppingservice.entity.Invoice;

import java.util.List;

public interface InvoiceService {

    public List<Invoice> findAllInvoices();

    public Invoice createInvoice(Invoice invoice);
    public Invoice getInvoice(Long id);
    public Invoice updateInvoice(Invoice invoice);
    public Invoice deleteInvoice(Invoice invoice);
}
