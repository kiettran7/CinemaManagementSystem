package com.ttk.cinema.services;

import com.ttk.cinema.DTOs.request.MovieRequest;
import com.ttk.cinema.DTOs.response.MovieResponse;
import com.ttk.cinema.POJOs.Genre;
import com.ttk.cinema.POJOs.Movie;
import com.ttk.cinema.POJOs.Tag;
import com.ttk.cinema.exceptions.AppException;
import com.ttk.cinema.exceptions.ErrorCode;
import com.ttk.cinema.mappers.MovieMapper;
import com.ttk.cinema.repositories.GenreRepository;
import com.ttk.cinema.repositories.MovieRepository;
import com.ttk.cinema.repositories.TagRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MovieService {
    MovieRepository movieRepository;
    MovieMapper movieMapper;
    CloudinaryService cloudinaryService;
    GenreRepository genreRepository;
    private final TagRepository tagRepository;

    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF', 'CUSTOMER')")
    public MovieResponse createMovie(MovieRequest request) throws IOException {
        Movie movie = movieMapper.toMovie(request);

        if (request.getFile() != null && !request.getFile().isEmpty()) {
            movie.setMovieImage(cloudinaryService.uploadFile(request.getFile()));
        }

        var genres = genreRepository.findAllById(request.getGenres());
        var tags = tagRepository.findAllById(request.getTags());

        System.out.println(request.getGenres());
        System.out.println(genres);
        System.out.println(request.getTags());
        System.out.println(tags);

        movie.setGenres(new HashSet<>(genres));
        movie.setTags(new HashSet<>(tags));

        movie = movieRepository.save(movie);

        return movieMapper.toMovieResponse(movie);
    }


    public MovieResponse getMovie(String movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_FOUND));
        return movieMapper.toMovieResponse(movie);
    }

    public List<MovieResponse> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(movieMapper::toMovieResponse)
                .toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteMovie(String movieId) {
        if (!movieRepository.existsById(movieId)) {
            throw new AppException(ErrorCode.MOVIE_NOT_FOUND);
        }
        movieRepository.deleteById(movieId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public MovieResponse updateMovie(String movieId, MovieRequest request) throws IOException {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_FOUND));

        movieMapper.updateMovie(movie, request);

        if (request.getFile() != null && !request.getFile().isEmpty()) {
            movie.setMovieImage(cloudinaryService.uploadFile(request.getFile()));
        }

        var genres = genreRepository.findAllById(request.getGenres());
        var tags = tagRepository.findAllById(request.getTags());

        movie.setGenres(new HashSet<>(genres));
        movie.setTags(new HashSet<>(tags));

        return movieMapper.toMovieResponse(movieRepository.save(movie));
    }
}

