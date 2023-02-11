package com.edu.miu.backend.repository;

import com.edu.miu.backend.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("from Reservation r where r.car.carId = :carId")
    List<Reservation> findAllByCarId(@Param("carId") Long carId);

    @Query("from Reservation r where r.user.id = :userId")
    List<Reservation> findAllByCustomerId(@Param("userId") Long userId);

    @Query("from Reservation r where r.user.id = :userId and r.car.carId = :carId")
    List<Reservation> findAllByCustomerIdAndCarId(@Param("userId") Long userId, @Param("carId") Long carId);

    @Query("from Reservation r where r.pickupDate = :pickUpDate and r.user.id = :userId")
    Reservation findByPickupDateAndUserId(@Param("pickUpDate")LocalDate pickUpDate, @Param("userId") Long userId);
}
