package com.devsenior.fBeltran.reservation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Request body for creating a reservation.
 *
 * @param clientName the name of the client
 * @param date       the reservation date
 * @param time       the reservation time
 * @param service    the service to reserve
 */
public record CreateReservationRequest(
        @NotBlank(message = "Client name must not be blank")
        String clientName,

        @NotNull(message = "Date must not be null")
        LocalDate date,

        @NotNull(message = "Time must not be null")
        LocalTime time,

        @NotBlank(message = "Service must not be blank")
        String service
) {}
