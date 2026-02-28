package com.devsenior.fBeltran.reservation.dto.response;

import com.devsenior.fBeltran.reservation.entity.ReservationStatus;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Response DTO for a reservation.
 *
 * @param id         the reservation identifier
 * @param clientName the name of the client
 * @param date       the reservation date
 * @param time       the reservation time
 * @param service    the reserved service
 * @param status     the reservation status (ACTIVA, CANCELADA)
 */
public record ReservationResponse(
        Long id,
        String clientName,
        LocalDate date,
        LocalTime time,
        String service,
        ReservationStatus status
) {}
