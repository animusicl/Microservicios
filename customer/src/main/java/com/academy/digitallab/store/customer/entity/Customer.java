package com.academy.digitallab.store.customer.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data @NoArgsConstructor
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 8, message = "DNI size should be 8")
    @Column(unique = true, length = 8, nullable = false)
    private String dni;

    @NotEmpty(message = "Name cannot be empty")
    @Column(nullable = false)
    private String fistName;

    @NotEmpty(message = "Lastname cannot be empty")
    @Column(nullable = false)
    private String lastName;

    @NotEmpty(message = "Email cannot be empty")
    @Email
    @Column(unique = true, nullable = false)
    private String email;
    private String photoUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Region region;
    private String state;

    //id, dni, firstName, lastName, email, photoUrl, region, state.

}
