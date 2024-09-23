package com.ttk.cinema.repositories;

import com.ttk.cinema.POJOs.ShowSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowScheduleRepository extends JpaRepository<ShowSchedule, Long> {
}

