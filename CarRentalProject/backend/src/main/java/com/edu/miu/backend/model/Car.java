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
    @GeneratedValue
    private Long carId;

    @NotNull
    @Column(nullable = false)
    private String regNo;

    @NotNull
    @Column(nullable = false)
    private Integer rentalFee;

    @NotNull
    @Column(nullable = false)
    private String year;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private CarModel model;

    private String imageCover;

    @NotNull
    @Column(nullable = false)
    private boolean status = false;

    @NotNull
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
