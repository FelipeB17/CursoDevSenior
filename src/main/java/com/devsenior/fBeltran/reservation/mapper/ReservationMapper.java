package com.devsenior.fBeltran.reservation.mapper;

import com.devsenior.fBeltran.reservation.dto.request.CreateReservationRequest;
import com.devsenior.fBeltran.reservation.dto.response.ReservationResponse;
import com.devsenior.fBeltran.reservation.entity.Reservation;
import com.devsenior.fBeltran.reservation.entity.ReservationStatus;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between {@link Reservation} entities and reservation DTOs.
 */
@Component
public class ReservationMapper {

    /**
     * Maps a create request DTO to a new reservation entity (no id, status ACTIVA).
     *
     * @param request the creation request (must not be null)
     * @return a new reservation entity with data from the request and status ACTIVA
     */
    public Reservation toEntity(CreateReservationRequest request) {
        var entity = new Reservation();
        entity.setClientName(request.clientName());
        entity.setDate(request.date());
        entity.setTime(request.time());
        entity.setService(request.service());
        entity.setStatus(ReservationStatus.ACTIVA);
        return entity;
    }

    /**
     * Maps a reservation entity to its response DTO.
     *
     * @param entity the reservation entity (must not be null)
     * @return the reservation response DTO
     */
    public ReservationResponse toResponse(Reservation entity) {
        return new ReservationResponse(
                entity.getId(),
                entity.getClientName(),
                entity.getDate(),
                entity.getTime(),
                entity.getService(),
                entity.getStatus()
        );
    }
}
