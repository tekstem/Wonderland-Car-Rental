package com.edu.miu.backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class ReservationDTO implements Serializable {
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickupDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;

    @NotNull
    private Long carId;

    @NotNull
    private Long customerId;
}
