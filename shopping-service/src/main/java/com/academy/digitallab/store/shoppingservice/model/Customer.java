package com.academy.digitallab.store.shoppingservice.model;

import lombok.Data;

@Data
public class Customer {
    private Long id;
    private String dni;
    private String fistName;
    private String lastName;
    private String email;
    private String photoUrl;
    private Region region;
    private String state;
}
