package com.academy.digitallab.store.shoppingservice.service;

import com.academy.digitallab.store.shoppingservice.entity.InvoiceItem;
import com.academy.digitallab.store.shoppingservice.repository.InvoiceItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceItemServiceImp implements  InvoiceItemService {

    private InvoiceItemRepository invoiceItemRepository;
    public InvoiceItemServiceImp(InvoiceItemRepository invoiceItemRepository) {
        this.invoiceItemRepository = invoiceItemRepository;
    }

    @Override
    public List<InvoiceItem> findAllInvoiceItem() {
        return invoiceItemRepository.findAll();
    }

    @Override
    public InvoiceItem createInvoiceItem(InvoiceItem invoiceItem) {
        return invoiceItemRepository.save(invoiceItem);
    }


    @Override
    public InvoiceItem getInvoiceItem(Long id) {
        return invoiceItemRepository.findById(id).orElse(null);
    }

    @Override
    public InvoiceItem updateInvoiceItem(InvoiceItem invoiceItem) {

        InvoiceItem invoiceItemDB = getInvoiceItem(invoiceItem.getId());
        if (invoiceItemDB == null) {
            return null;
        }
        invoiceItemDB.setQuantity(invoiceItem.getQuantity());
        invoiceItemDB.setPrice(invoiceItem.getPrice());
        invoiceItemDB.setProductId(invoiceItem.getProductId());

        return invoiceItemRepository.save(invoiceItemDB);
    }

    @Override
    public void deleteInvoiceItem (Long id) {
        InvoiceItem invoiceItem = getInvoiceItem(id);
        invoiceItemRepository.delete(invoiceItem);
    }
}
