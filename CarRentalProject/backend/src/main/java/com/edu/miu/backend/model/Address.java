package com.edu.miu.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "street cannot be null or blank or empty")
    @Column(nullable = false)
    private String street;

    @NotBlank(message = "city cannot be null or blank or empty")
    @Column(nullable = false)
    private String city;

    @NotBlank(message = "zip cannot be null or blank or empty")
    @Column(nullable = false)
    private String zip;
}
