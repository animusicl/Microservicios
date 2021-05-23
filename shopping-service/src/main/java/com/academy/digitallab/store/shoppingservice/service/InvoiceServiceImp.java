package com.academy.digitallab.store.shoppingservice.service;

import com.academy.digitallab.store.shoppingservice.client.CustomerClient;
import com.academy.digitallab.store.shoppingservice.client.ProductClient;
import com.academy.digitallab.store.shoppingservice.entity.Invoice;
import com.academy.digitallab.store.shoppingservice.entity.InvoiceItem;
import com.academy.digitallab.store.shoppingservice.model.Customer;
import com.academy.digitallab.store.shoppingservice.model.Product;
import com.academy.digitallab.store.shoppingservice.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImp implements InvoiceService {

    private InvoiceRepository invoiceRepository;
    private CustomerClient customerClient;
    private ProductClient productClient;

    public InvoiceServiceImp(InvoiceRepository invoiceRepository, CustomerClient customerClient, ProductClient productClient) {
        this.invoiceRepository = invoiceRepository;
        this.customerClient = customerClient;
        this.productClient = productClient;
    }

    @Override
    public List<Invoice> findAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice createInvoice(Invoice invoice) { // idempotent

        Invoice invoiceDB = invoiceRepository.findByInvoiceNumber(invoice.getInvoiceNumber());
        if(invoiceDB != null){
            return invoiceDB;
        }

        invoice.setState("CREATED");
        invoiceDB = invoiceRepository.save(invoice);
        invoiceDB.getItems().forEach(invoiceItem -> {
            productClient.updateSock(invoiceItem.getProductId(), invoiceItem.getQuantity() * -1);
        });
        return invoiceDB;
    }

    @Override
    public Invoice getInvoice(Long id) {
    Invoice invoice = invoiceRepository.findById(id).orElse(null);
    if(null != invoice) {
        Customer customer = customerClient.getCustomer(invoice.getCustomerId()).getBody(); //el body es un cliente
        invoice.setCustomer(customer);
        List<InvoiceItem> listItem = invoice.getItems().stream().map(invoiceItem -> {
            Product product = productClient.getProduct(invoiceItem.getProductId()).getBody();
            invoiceItem.setProduct(product);
            return invoiceItem;
        }).collect(Collectors.toList());
        invoice.setItems(listItem);
    }
        return invoice;
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        Invoice invoiceDB = getInvoice(invoice.getId());
        if (invoiceDB == null){
            return null;
        }

        invoiceDB.setCustomerId(invoice.getCustomerId());
        invoiceDB.setDescription(invoice.getDescription());
        invoiceDB.setInvoiceNumber(invoice.getInvoiceNumber());
        invoiceDB.getItems().clear();
        invoiceDB.setItems(invoice.getItems());

        return invoiceRepository.save(invoiceDB);
    }

    @Override
    public Invoice deleteInvoice(Invoice invoice) {
        Invoice invoiceDB = getInvoice(invoice.getId());
        if (invoiceDB == null){
            return null;
        }
        invoiceDB.setState("DELETED");
        return invoiceRepository.save(invoiceDB);
    }
}
