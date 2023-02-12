package com.edu.miu.backend.repository;

import com.edu.miu.backend.model.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarBrandRepository extends JpaRepository<CarBrand, Long> { }
