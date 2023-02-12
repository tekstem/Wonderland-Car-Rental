package com.edu.miu.backend.services.impl;

import com.edu.miu.backend.repository.CarBrandRepository;
import com.edu.miu.backend.services.CarBrandService;
import com.edu.miu.backend.model.CarBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CarBrandServiceImp implements CarBrandService {
    private CarBrandRepository carBrandRepository;

    @Autowired
    public void setCarBrandRepository(CarBrandRepository carBrandRepository) {
        this.carBrandRepository = carBrandRepository;
    }

    public List<CarBrand> findAll() {
        return carBrandRepository.findAll();
    }

    public CarBrand findById(Long brandId) {
        return carBrandRepository.findById(brandId)
                .orElse(null);
    }

    public CarBrand createBrand(CarBrand carBrand) {
        return carBrandRepository.save(carBrand);
    }
}
