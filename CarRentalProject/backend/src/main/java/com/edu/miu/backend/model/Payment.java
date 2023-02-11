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
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String paymentID;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull
    @Column(nullable = false)
    private Double amount;

    @NotNull
    @Column(nullable = false)
    private Boolean status;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
}
