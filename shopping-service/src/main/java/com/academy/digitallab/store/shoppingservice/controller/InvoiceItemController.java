package com.academy.digitallab.store.shoppingservice.controller;

import com.academy.digitallab.store.shoppingservice.entity.InvoiceItem;
import com.academy.digitallab.store.shoppingservice.service.InvoiceItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/invoiceItem")
public class InvoiceItemController {

    private InvoiceItemService invoiceItemService;

    public InvoiceItemController(InvoiceItemService invoiceItemService) {
        this.invoiceItemService = invoiceItemService;
    }

    @GetMapping
    public ResponseEntity<List<InvoiceItem>> getAllInvoiceItems() {

        List<InvoiceItem> invoiceItems = invoiceItemService.findAllInvoiceItem();

        return (invoiceItems.isEmpty()) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(invoiceItems);

    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceItem> getInvoiceItem(@PathVariable("id") Long id) {
        InvoiceItem invoiceItem = invoiceItemService.getInvoiceItem(id);
        return (invoiceItem == null) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(invoiceItem);
    }

    @PostMapping
    public ResponseEntity<InvoiceItem> createInvoiceItem (@RequestBody InvoiceItem invoiceItem) {
        InvoiceItem invoiceItemDB = invoiceItemService.createInvoiceItem(invoiceItem);
        return (invoiceItemDB == null) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(invoiceItemService.createInvoiceItem(invoiceItemDB));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceItem> updateInvoiceItem (
            @PathVariable("id") Long id,
            @RequestBody InvoiceItem invoiceItem) {
        InvoiceItem invoiceItemDB = invoiceItemService.getInvoiceItem(id);
        return (invoiceItemDB == null) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(invoiceItemService.updateInvoiceItem(invoiceItem));
    }

    @DeleteMapping("/{id}")
    public void deleteInvoiceItem (@PathVariable("id") Long id) {
        invoiceItemService.deleteInvoiceItem(id);
    }
}
