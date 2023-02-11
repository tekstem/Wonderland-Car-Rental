package com.edu.miu.backend.services.impl;

import com.edu.miu.backend.repository.CarRepository;
import com.edu.miu.backend.services.CarBrandService;
import com.edu.miu.backend.services.CarService;
import com.edu.miu.backend.model.Car;
import com.edu.miu.backend.dto.CarDTO;
import com.edu.miu.backend.model.CarModel;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;
    private CarBrandService carBrandService;

    @Autowired
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Autowired
    public void setCarBrandService(CarBrandService carBrandService) {
        this.carBrandService = carBrandService;
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Car createCar(CarDTO carDTO) {
        Car car = new Car();

        car.setName(carDTO.getName());
        car.setYear(carDTO.getYear());
        car.setRegNo(carDTO.getRegNo());
        car.setModel(carDTO.getModel());
        car.setRentalFee(carDTO.getRentalFee());
        car.setImageCover(carDTO.getImageCover());
        car.setBrand(carBrandService.findById(carDTO.getCarBrand()));

        return carRepository.save(car);
    }

    public Car findById(Long carId) {
        return carRepository.findById(carId).orElse(null);
    }

    public List<Car> findAllByBrandName(String brandName) {
        return carRepository.findAllByBrandName(brandName);
    }

    public List<Car> findAllByModel(CarModel carModel) {
        return carRepository.findAllByModel(carModel);
    }

    public Car updateCar(Long carId, Car car) {
        Car attachedCar = findById(carId);
        if (attachedCar == null) {
            return null;
        }
        attachedCar.setYear(car.getYear());
        attachedCar.setName(car.getName());
        attachedCar.setBrand(car.getBrand());
        attachedCar.setModel(car.getModel());
        attachedCar.setRegNo(car.getRegNo());
        attachedCar.setRentalFee(car.getRentalFee());
        attachedCar.setIsReserved(car.getIsReserved());

        return carRepository.save(car);
    }

    public List<Car> findCarsByYear(String year) {
        return carRepository.findAllByYear(year);
    }
}
