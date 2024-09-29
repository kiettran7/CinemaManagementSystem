package com.ttk.cinema.repositories;

import com.ttk.cinema.POJOs.ShowRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRoomRepository extends JpaRepository<ShowRoom, String> {
}
