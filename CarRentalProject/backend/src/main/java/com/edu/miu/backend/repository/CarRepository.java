package com.edu.miu.backend.repository;

import com.edu.miu.backend.model.Car;
import com.edu.miu.backend.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByModel(@Param("model") CarModel model);

    @Query("select c from Car c where c.brand.name = :brandName")
    List<Car> findAllByBrandName(@Param("brandName") String brandName);

    List<Car> findAllByYear(@Param("year") String year);
}
