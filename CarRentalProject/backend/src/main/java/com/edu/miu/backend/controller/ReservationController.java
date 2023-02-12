package com.edu.miu.backend.controller;

import com.edu.miu.backend.exception.CustomException;
import com.edu.miu.backend.services.ReservationService;
import com.edu.miu.backend.dto.ReservationDTO;
import com.edu.miu.backend.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private ReservationService reservationService;

    @Autowired
    public void setReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<Reservation> makeCarReservation(
            @RequestBody ReservationDTO reservationDTO,
            @AuthenticationPrincipal User loggedInUser
            ) throws CustomException {
        return new ResponseEntity<>(
                reservationService.create(reservationDTO, loggedInUser),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> findReservations() {
        return ResponseEntity.ok(reservationService.findAllReservations());
    }
    @GetMapping("/{reservationId}")
    public ResponseEntity<Reservation> findReservationById(
            @PathVariable("reservationId") Long reservationId
    ) {
        return ResponseEntity.ok(
                reservationService.findById(reservationId)
        );
    }

    @GetMapping("/filter") // localhost:8080/reservations/filter?userId=1&pickupDate=2001-11-11
    public ResponseEntity<Reservation> getReservationByReturnDateAndUserId(
            @RequestParam("userId") Long userId,
            @RequestParam("pickupDate") LocalDate pickupDate
    ) {
        return ResponseEntity.ok(
                reservationService.findByPickupDateAndUserId(pickupDate, userId)
        );
    }

    @PutMapping("/{reservationId}")
    public ResponseEntity<Reservation> updateReservation(
            @RequestBody ReservationDTO reservationDTO,
            @PathVariable("reservationId") Long reservationId
    ) throws CustomException {
        return new ResponseEntity<>(
                reservationService.update(reservationId, reservationDTO),
                HttpStatus.OK
        );
    }

    @PutMapping("/{reservationId}/confirm")
    public ResponseEntity<Reservation> confirmReservation(
            @PathVariable("reservationId") Long reservationId
    ) throws CustomException {
        return new ResponseEntity<>(
                reservationService.confirm(reservationId),
                HttpStatus.OK
        );
    }

    @PutMapping("/{reservationId}/cancel")
    public ResponseEntity<Reservation> cancelReservation(
            @PathVariable("reservationId") Long reservationId
    ) throws CustomException {
        return new ResponseEntity<>(
                reservationService.cancel(reservationId),
                HttpStatus.OK
        );
    }

    @PutMapping("/{reservationId}/carPicked")
    public ResponseEntity<Reservation> markReservationInProgress(
            @PathVariable("reservationId") Long reservationId
    ) throws CustomException {
        return new ResponseEntity<>(
                reservationService.makerInProgress(reservationId),
                HttpStatus.OK
        );
    }

    @PutMapping("/{reservationId}/carReturned")
    public ResponseEntity<Reservation> makeReservationCompleted(
            @PathVariable("reservationId") Long reservationId
    ) throws CustomException {
        return new ResponseEntity<>(
                reservationService.makeReservationCompleted(reservationId),
                HttpStatus.OK
        );
    }
}
