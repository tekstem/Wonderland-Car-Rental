package com.edu.miu.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    @NotNull(message = "regno cannot be null")
    @Column(nullable = false)
    private String regNo;

    @NotNull(message = "rental fee cannot be null")
    @Column(nullable = false)
    private Integer rentalFee;

    @NotNull(message = "year cannot be null")
    @Column(nullable = false)
    private String year;

    @NotNull(message = "name cannot be null")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "model cannot be null")
    @Column(nullable = false)
    private CarModel model;

    private String imageCover;

    @NotNull(message = "status cannot be null")
    @Column(nullable = false)
    private boolean status = false;

    @NotNull(message = "brand cannot be null")
    @ManyToOne
    @JoinColumn(name = "brandId", nullable = false)
    private CarBrand brand;

    private Boolean isReserved = false;
    @Override
    public String toString() {
        return "Car {" + "year = '" + year + '\'' +
                ", name = '" + name + '\'' +
                ", model = '" + model + '\'' +
                ", brand = '" + brand.getName() + '}';
    }
}
