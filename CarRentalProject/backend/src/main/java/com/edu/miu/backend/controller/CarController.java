package com.edu.miu.backend.controller;

import com.edu.miu.backend.dto.CarDTO;
import com.edu.miu.backend.model.Car;
import com.edu.miu.backend.model.CarModel;
import com.edu.miu.backend.model.Reservation;
import com.edu.miu.backend.services.CarService;
import com.edu.miu.backend.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins ="http://localhost:3000/")
@RestController
@RequestMapping("/cars")
public class CarController {
    private CarService carService;
    private ReservationService reservationService;

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @Autowired
    public void setReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.ok(carService.findAll());
    }

    @GetMapping("/{carId}/reservations")
    public ResponseEntity<List<Reservation>> getAllCarReservations(
            @PathVariable("carId") Long carId
    ) {
        return ResponseEntity.ok(
                reservationService.findAllByCarId(carId)
        );
    }

    @GetMapping("/byYear")
    public ResponseEntity<List<Car>> getCarsByYear(@RequestParam("year") String year) {
        return new ResponseEntity<>(carService.findCarsByYear(year), HttpStatus.OK);
    }

    @GetMapping("/byBrand")
    public ResponseEntity<List<Car>> getCarsByBrand(@RequestParam("brand") String brand) {
        return new ResponseEntity<>(carService.findAllByBrandName(brand), HttpStatus.OK);
    }

    @GetMapping("/byModel")
    public ResponseEntity<List<Car>> getCarsByBrand(@RequestParam("model") CarModel carModel) {
        return new ResponseEntity<>(carService.findAllByModel(carModel), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody CarDTO carDTO) {
        return new ResponseEntity<>(carService.createCar(carDTO), HttpStatus.CREATED);
    }
}
