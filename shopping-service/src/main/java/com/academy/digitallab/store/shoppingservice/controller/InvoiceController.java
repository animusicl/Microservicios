package com.academy.digitallab.store.shoppingservice.controller;


import com.academy.digitallab.store.shoppingservice.entity.Invoice;
import com.academy.digitallab.store.shoppingservice.service.InvoiceService;
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
@RequestMapping(path = "api/v1/invoice")
public class InvoiceController {

    private InvoiceService invoiceService;
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public ResponseEntity <List<Invoice>> getAllInvoices() {
        List <Invoice> invoices = invoiceService.findAllInvoices();

        return (invoices.isEmpty()) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(invoices);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoice (@PathVariable("id") Long id) {
        log.info("Fetching Invoice with id {}", id);
        Invoice invoice = invoiceService.getInvoice(id);
        if (invoice == null) {
            log.error("Invoice with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(invoice);
    }

    @PostMapping
    public ResponseEntity<Invoice> createInvoice (
            @Valid @RequestBody Invoice invoice,
            BindingResult result) {

        log.info("Creating invoice : {}", invoice);
        if (result.hasErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "something got wrong");
        }
        Invoice invoiceDB = invoiceService.createInvoice(invoice);
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceDB);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice (
            @PathVariable("id") Long id,
            @RequestBody Invoice invoice) {

        log.info("Updating invoice with id {}.", id);

        invoice.setId(id);
        Invoice currentInvoice = invoiceService.updateInvoice(invoice);

        if (currentInvoice == null) {
            log.error("Unable to update. Invoice with id {} not foun.d", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(currentInvoice);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Invoice> deleteInvoice (@PathVariable("id") Long id) {
        log.info("Fetching and deleting invoice with id {}", id);

        Invoice invoice = invoiceService.getInvoice(id);
        if (invoice == null) {
            log.info("Unable to delete. Invoice with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(invoiceService.deleteInvoice(invoice));
    }




}
