package com.edu.miu.backend.seed;

import com.edu.miu.backend.services.CarBrandService;
import com.edu.miu.backend.services.CarService;
import com.edu.miu.backend.dto.CarDTO;
import com.edu.miu.backend.model.CarBrand;
import com.edu.miu.backend.model.CarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
@Transactional(propagation = Propagation.REQUIRED)
public class CarAndBrandData {
    private CarService carService;
    private final Random rand = new Random();
    private CarBrandService carBrandService;
    Stack<String> images = new Stack<>(){{
        add("https://images.pexels.com/photos/112475/pexels-photo-112475.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500");
        add("https://images.pexels.com/photos/4077271/pexels-photo-4077271.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500");
        add("https://images.pexels.com/photos/4502384/pexels-photo-4502384.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500");
        add("https://images.pexels.com/photos/4501407/pexels-photo-4501407.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500");
        add("https://images.pexels.com/photos/4928606/pexels-photo-4928606.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500");
        add("https://images.pexels.com/photos/2838729/pexels-photo-2838729.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500");
        add("https://images.pexels.com/photos/241316/pexels-photo-241316.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
        add("https://images.pexels.com/photos/38637/car-audi-auto-automotive-38637.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
    }};

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @Autowired
    public void setCarBrandService(CarBrandService carBrandService) {
        this.carBrandService = carBrandService;
    }

    private void loadCarsAndCarBrands() {
        List<String> brands = new ArrayList<>(){{
            add("GMC");
            add("Ford");
            add("Mazda");
            add("Toyota");
            add("Nissan");
            add("Porsche");
        }};

        HashMap<String, String> cars = new HashMap<>(){{
            put("GMC", "Cameo");
            put("Mazda", "XC 60");
            put("Ford", "Mustang");
            put("Toyota", "Supra");
            put("Nissan", "Qashqai");
            put("Porsche", "Taycan");
        }};


        for (String brandName : brands) {
            CarBrand brand = new CarBrand();
            brand.setName(brandName);
            brand.setDescription(brandName + " Lorem ipsum is used plo tut.");
            brand = carBrandService.createBrand(brand);

            if (cars.containsKey(brandName)) {
                CarDTO car = new CarDTO();

                car.setImageCover(images.pop());
                car.setName(cars.get(brandName));
                car.setCarBrand(brand.getBrandId());
                car.setYear("200" + brandName.length());
                car.setRentalFee(brandName.length() * 20);
                car.setRegNo("XII ML" + brandName.length() + rand.nextInt(100));
                // randomly choose car model basing on the brands name length
                car.setModel(brandName.length() % 2 == 0 ? CarModel.SUV: CarModel.SEDAN);

                carService.createCar(car);
            }
        }
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        if (carBrandService.findAll().size() == 0) {
            loadCarsAndCarBrands();
        }
    }
}
