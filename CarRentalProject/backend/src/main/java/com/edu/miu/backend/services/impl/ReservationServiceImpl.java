package com.edu.miu.backend.services.impl;

import com.edu.miu.backend.exception.CustomException;
import com.edu.miu.backend.model.*;
import com.edu.miu.backend.repository.ReservationRepository;
import com.edu.miu.backend.services.CarService;
import com.edu.miu.backend.services.ReservationService;
import com.edu.miu.backend.services.UserService;
import com.edu.miu.backend.dto.ReservationDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    private CarService carService;
    private UserService userService;
    private ReservationRepository reservationRepository;

    @Autowired
    public void setReservationRepository(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Reservation findById(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .orElse(null);
    }

    @Override
    public List<Reservation> findAllReservations() {
        return reservationRepository.findAll(Sort.by(Sort.Direction.DESC,"pickupDate"));
    }

    public List<Reservation> findAllByCarId(Long carId) {
        return reservationRepository
                .findAllByCarId(carId);
    }

    public List<Reservation> findAllByCustomerId(Long customerId) {
        return reservationRepository
                .findAllByCustomerId(customerId);
    }

    public Reservation cancel(Long reservationId) throws CustomException {
        Reservation reservation = updateReservationStatus(
                reservationId,
                ReservationStatus.CANCELLED
        );
        // make car available ready to be reserved again.
        Car car = carService.findById(reservation.getCar().getCarId());
        car.setIsReserved(false);
        carService.updateCar(car.getCarId(), car);

        return reservation;
    }

    public Reservation confirm(Long reservationId) throws CustomException {
        return updateReservationStatus(
                reservationId,
                ReservationStatus.CONFIRMED
        );
    }

    public Reservation makeReservationCompleted(Long reservationId) throws CustomException {
        Reservation reservation = updateReservationStatus(
                reservationId,
                ReservationStatus.COMPLETED
        );
        Car car = carService.findById(reservation.getCar().getCarId());

        // make car available ready to be reserved again.
        car.setIsReserved(false);
        carService.updateCar(car.getCarId(), car);

        return reservation;
    }

    private boolean checkIfCustomerHasPendingReservations(User customer) {
        List<Reservation> foundReservations = findAllByCustomerId(customer.getId());
        return foundReservations.stream()
                .filter(reservation ->
                        reservation.getStatus() != ReservationStatus.CANCELLED &&
                        reservation.getStatus() != ReservationStatus.COMPLETED
                )
                .toList().size() != 0;
    }

    public Reservation create(
            ReservationDTO reservationDTO,
            org.springframework.security.core.userdetails.User loggedInUser
    ) throws CustomException {
        Reservation reservation = new Reservation();
        mapReservationDtoToReservation(reservationDTO, reservation);
        User customer = userService.findByUsername(loggedInUser.getUsername());
        if (customer.getRole() != Role.CUSTOMER) {
            throw new CustomException("User making a reservation is not a customer.");
        }
        if (checkIfCustomerHasPendingReservations(customer)) {
            throw new CustomException("Sorry you have reservation that is still active.");
        }

        reservation.setStatus(ReservationStatus.STARTED);

        Car car = carService.findById(reservationDTO.getCarId());
        // mark car's available to be reserved.
        car.setIsReserved(true);
        carService.updateCar(car.getCarId(), car);

        return reservationRepository.save(reservation);
    }

    public Reservation makerInProgress(Long reservationId) throws CustomException {
        return updateReservationStatus(
                reservationId,
                ReservationStatus.PENDING
        );
    }

    public Reservation update(Long reservationId, ReservationDTO reservationDTO) throws CustomException {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElse(null);
        if (reservation == null) {
            throw new CustomException("Reservation with provided ID does not exist.");
        }
        mapReservationDtoToReservation(reservationDTO, reservation);

        return reservationRepository.save(reservation);
    }

    public Reservation findByPickupDateAndUserId(LocalDate pickupDate, Long userId) {
        return reservationRepository.findByPickupDateAndUserId(pickupDate, userId);
    }

    private void mapReservationDtoToReservation(
            ReservationDTO reservationDTO,
            Reservation reservation
    ) throws CustomException {
        Car car = carService.findById(reservationDTO.getCarId());
        User customer = userService.findById(reservationDTO.getCustomerId());

        if (car == null) {
            throw new CustomException("Car with provided ID does not exist.");
        }
        if (customer == null) {
            throw new CustomException("Customer with provided ID does not exist.");
        }
        if (customer.getRole() != Role.CUSTOMER) {
            throw new CustomException("User making a reservation is not a customer.");
        }
        if (reservationDTO.getPickupDate().isBefore(LocalDate.now())) {
            throw new CustomException("Pickup date should be in future");
        }
        if (
                reservationDTO.getReturnDate().isBefore(LocalDate.now()) ||
                reservationDTO.getReturnDate().isEqual(reservationDTO.getPickupDate()) ||
                reservationDTO.getReturnDate().isBefore(reservationDTO.getPickupDate())
        ) {
            throw new CustomException("Return date should be in future and greater than return date");
        }

        reservation.setCar(car);
        reservation.setUser(customer);
        reservation.setPickupDate(reservationDTO.getPickupDate());
        reservation.setReturnDate(reservationDTO.getReturnDate());
    }

    private Reservation updateReservationStatus(Long reservationId, ReservationStatus status) throws CustomException {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElse(null);
        if (reservation == null) {
            throw new CustomException("Reservation with provided ID does not exist.");
        }
        reservation.setStatus(status);

        Car car = reservation.getCar();
        car.setStatus(status == ReservationStatus.CONFIRMED);

        carService.updateCar(car.getCarId(), car);
        return reservationRepository.save(reservation);
    }
}
