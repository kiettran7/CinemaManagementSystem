package com.ttk.cinema.controllers;

import com.ttk.cinema.POJOs.Movie;
import com.ttk.cinema.services.MovieService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminController {
    MovieService movieService;

    @GetMapping("/dashboard")
    public String getDashboard(Model model) {
        // Load necessary data for the dashboard
        return "admin/dashboard";
    }

    @GetMapping("/movies")
    public String getMoviesPage(Model model) {
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        return "admin/movies";
    }

    // Similarly, create methods for managing other sections like schedules, customers, tickets, etc.
}

