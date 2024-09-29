package com.ttk.cinema.repositories;

import com.ttk.cinema.POJOs.ShowEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowEventRepository extends JpaRepository<ShowEvent, String> {
}
