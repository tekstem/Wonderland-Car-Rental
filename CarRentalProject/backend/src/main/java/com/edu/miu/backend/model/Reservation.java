package com.edu.miu.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "reservations")
public class    Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "pickup date cannot be null")
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickupDate;

    @NotNull(message = "local date cannot be null")
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;

    @NotNull(message = "status cannot be null")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

//    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @ManyToOne
    @JoinColumn(name = "carId")
    private Car car;
}
