package com.academy.digitallab.store.shoppingservice.entity;

import com.academy.digitallab.store.shoppingservice.model.Product;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Data
@Entity
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "Stock should be greater than 0")
    private Double quantity;

    @Positive(message = "Price should be greater than 0")
    private Double price;

    private Long productId;

    @Transient
    private Double subTotal;

    @Transient
    private Product product;

    public Double getSubTotal() {

        return (this.price > 0 && this.quantity > 0) ?
                this.quantity * this.price :
                (double) 0;
    }

    public InvoiceItem() {
        this.quantity = (double) 0;
        this.price=(double) 0;
    }
}
