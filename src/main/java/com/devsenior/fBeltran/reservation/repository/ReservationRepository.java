package com.devsenior.fBeltran.reservation.repository;

import com.devsenior.fBeltran.reservation.entity.Reservation;
import com.devsenior.fBeltran.reservation.entity.ReservationStatus;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for {@link Reservation}.
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    /**
     * Checks whether a reservation already exists for the given date and time.
     *
     * @param date the reservation date (must not be null)
     * @param time the reservation time (must not be null)
     * @return {@code true} if a reservation exists for the given date and time; {@code false} otherwise
     */
    boolean existsByDateAndTime(LocalDate date, LocalTime time);

    /**
     * Checks whether an active reservation already exists for the given date and time.
     *
     * @param date   the reservation date (must not be null)
     * @param time   the reservation time (must not be null)
     * @param status the reservation status to filter by (e.g. ACTIVA)
     * @return {@code true} if a reservation exists for the given date, time and status; {@code false} otherwise
     */
    boolean existsByDateAndTimeAndStatus(LocalDate date, LocalTime time, ReservationStatus status);
}

