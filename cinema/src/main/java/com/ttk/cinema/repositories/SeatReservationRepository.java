package com.ttk.cinema.repositories;

import com.ttk.cinema.POJOs.SeatReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatReservationRepository extends JpaRepository<SeatReservation, Long> {
}
