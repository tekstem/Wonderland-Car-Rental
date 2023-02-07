package edu.miu.cs.cs425.carsystem.service.imp;

import edu.miu.cs.cs425.carsystem.model.Car;
import edu.miu.cs.cs425.carsystem.repository.CarRepository;
import edu.miu.cs.cs425.carsystem.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired

    private CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car addNewCar(Car newCar) {
        return carRepository.save(newCar);
    }

    @Override
    public Car getByById(Integer carId) {
        return carRepository.findById(carId).orElse(null);
    }

    @Override
    public Car updatecar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void deleteCarById(Integer carId) {
        carRepository.deleteById(carId);
    }


//    @Override
//    public Iterable<Car> getAllCars() {
//        return carRepository.findAll();
//    }
   @Override
    public Iterable<Car> getAllCars() {
        return carRepository.findAll(Sort.by(Sort.Direction.ASC,"registrationNumber"));
    }

    @Override
    public Page<Car> getAllCarPaged(Integer pageNo) {
        return carRepository.findAll(PageRequest.of(pageNo,3, Sort.by(Sort.Direction.ASC, "registrationNumber")));
    }

    @Override
    public List<Car> searchCarByRegistrationNumberOrModelOrMakeOrYear(String searchString) {
        return carRepository.searchCarByRegistrationNumberOrModelOrMakeOrYear(searchString,searchString,searchString,searchString);
    }


}
