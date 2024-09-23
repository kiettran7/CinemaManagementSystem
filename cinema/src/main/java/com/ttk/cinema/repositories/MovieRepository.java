package com.ttk.cinema.repositories;

import com.ttk.cinema.POJOs.Genre;
import com.ttk.cinema.POJOs.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Set<Movie> findAllByGenres_IdAndTags_Id(Long genreId, Long tagId);
    Set<Movie> findAllByGenres(Long genreId);
    Set<Movie> findAllByTag(Long tagId);
}
