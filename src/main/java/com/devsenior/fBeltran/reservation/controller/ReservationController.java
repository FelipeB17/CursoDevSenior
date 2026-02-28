package com.devsenior.fBeltran.reservation.controller;

import com.devsenior.fBeltran.reservation.dto.request.CreateReservationRequest;
import com.devsenior.fBeltran.reservation.dto.response.ReservationResponse;
import com.devsenior.fBeltran.reservation.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for reservation operations.
 * Base path: {@code /reservas}.
 */
@RestController
@RequestMapping("/reservas")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * Lists all reservations.
     *
     * @return list of all reservations (200 OK)
     */
    @GetMapping
    public ResponseEntity<List<ReservationResponse>> listAll() {
        var reservations = reservationService.findAll();
        return ResponseEntity.ok(reservations);
    }

    /**
     * Creates a new reservation.
     *
     * @param request the creation payload (validated)
     * @return the created reservation (201 Created)
     */
    @PostMapping
    public ResponseEntity<ReservationResponse> create(@Valid @RequestBody CreateReservationRequest request) {
        var created = reservationService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Cancels a reservation by id.
     *
     * @param id the reservation id (path variable)
     * @return the cancelled reservation (200 OK)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ReservationResponse> cancel(@PathVariable Long id) {
        var cancelled = reservationService.cancelById(id);
        return ResponseEntity.ok(cancelled);
    }
}
