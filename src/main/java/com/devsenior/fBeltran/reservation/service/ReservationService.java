package com.devsenior.fBeltran.reservation.service;

import com.devsenior.fBeltran.reservation.dto.request.CreateReservationRequest;
import com.devsenior.fBeltran.reservation.dto.response.ReservationResponse;
import com.devsenior.fBeltran.reservation.entity.ReservationStatus;
import com.devsenior.fBeltran.reservation.exception.BusinessRuleException;
import com.devsenior.fBeltran.reservation.exception.ResourceNotFoundException;
import com.devsenior.fBeltran.reservation.mapper.ReservationMapper;
import com.devsenior.fBeltran.reservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service containing the business logic for reservations.
 */
@Service
public class ReservationService {

    private static final String SLOT_ALREADY_TAKEN = "A reservation already exists for date %s and time %s";
    private static final String RESERVATION_NOT_FOUND = "Reservation with id %d not found";
    private static final String ALREADY_CANCELLED = "Reservation with id %d is already cancelled";

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    public ReservationService(ReservationRepository reservationRepository, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    /**
     * Returns all reservations in the system.
     *
     * @return list of all reservations as response DTOs (may be empty)
     */
    public List<ReservationResponse> findAll() {
        return reservationRepository.findAll().stream()
                .map(reservationMapper::toResponse)
                .toList();
    }

    /**
     * Creates a new reservation only if no other active reservation exists for the same date and time.
     *
     * @param request the creation payload (validated)
     * @return the created reservation as a DTO with generated id and status ACTIVA
     * @throws BusinessRuleException if an active reservation already exists for the given date and time
     */
    @Transactional
    public ReservationResponse create(CreateReservationRequest request) {
        if (reservationRepository.existsByDateAndTimeAndStatus(
                request.date(), request.time(), ReservationStatus.ACTIVA)) {
            throw new BusinessRuleException(
                    SLOT_ALREADY_TAKEN.formatted(request.date(), request.time()));
        }
        var reservation = reservationMapper.toEntity(request);
        var saved = reservationRepository.save(reservation);
        return reservationMapper.toResponse(saved);
    }

    /**
     * Cancels a reservation by its id. The reservation must exist and must not already be cancelled.
     *
     * @param id the reservation id (must not be null)
     * @return the updated reservation as a DTO with status CANCELADA
     * @throws ResourceNotFoundException if no reservation exists for the given id
     * @throws BusinessRuleException       if the reservation is already cancelled
     */
    @Transactional
    public ReservationResponse cancelById(Long id) {
        var reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESERVATION_NOT_FOUND.formatted(id)));
        if (reservation.getStatus() == ReservationStatus.CANCELADA) {
            throw new BusinessRuleException(ALREADY_CANCELLED.formatted(id));
        }
        reservation.setStatus(ReservationStatus.CANCELADA);
        var saved = reservationRepository.save(reservation);
        return reservationMapper.toResponse(saved);
    }
}
