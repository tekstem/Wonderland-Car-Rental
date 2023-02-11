package com.edu.miu.backend.dto;

import com.edu.miu.backend.model.CarModel;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class CarDTO implements Serializable {
    private String year;
    private String name;
    private String regNo;
    private Long carBrand;
    private CarModel model;
    private String imageCover;
    private Integer rentalFee;
}
