package com.edu.miu.backend.services;

import com.edu.miu.backend.model.CarBrand;

import java.util.List;

public interface CarBrandService {
    List<CarBrand> findAll();
    CarBrand findById(Long brandId);
    CarBrand createBrand(CarBrand carBrand);
}
