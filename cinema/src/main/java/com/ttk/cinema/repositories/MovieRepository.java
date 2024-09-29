package com.ttk.cinema.repositories;

import com.ttk.cinema.POJOs.Genre;
import com.ttk.cinema.POJOs.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
}
