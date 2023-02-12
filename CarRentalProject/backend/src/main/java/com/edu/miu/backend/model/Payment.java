package com.edu.miu.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "payment id cannot be blank or null")
    @Column(nullable = false)
    private String paymentID;

    @NotNull(message = "date cannot be null")
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull(message = "amount cannot be null")
    @Column(nullable = false)
    private Double amount;

    @NotNull(message = "status cannot be null")
    @Column(nullable = false)
    private Boolean status;

    @NotNull(message = "payment type cannot be null")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
}
