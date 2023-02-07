package edu.miu.cs.cs425.carsystem.service;

import edu.miu.cs.cs425.carsystem.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface CarService {
  public Car addNewCar(Car newCar);
  public Car getByById(Integer carId);
  public Car updatecar(Car car);
  public void deleteCarById(Integer carId);
  public Iterable<Car> getAllCars();
 public Page<Car> getAllCarPaged(Integer pageNo);
public List<Car>  searchCarByRegistrationNumberOrModelOrMakeOrYear(String searchString);

}
