package com.academy.digitallab.store.shoppingservice.service;

import com.academy.digitallab.store.shoppingservice.entity.InvoiceItem;

import java.util.List;

public interface InvoiceItemService {

    public List<InvoiceItem> findAllInvoiceItem();

    public InvoiceItem createInvoiceItem(InvoiceItem invoiceItem);
    public InvoiceItem getInvoiceItem(Long id);
    public InvoiceItem updateInvoiceItem(InvoiceItem invoiceItem);
    public void deleteInvoiceItem(Long id);
}
