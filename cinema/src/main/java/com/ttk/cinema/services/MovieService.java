package com.ttk.cinema.services;

import com.ttk.cinema.DTOs.request.creation.MovieCreationRequest;
import com.ttk.cinema.DTOs.request.update.MovieUpdateRequest;
import com.ttk.cinema.DTOs.response.MovieResponse;
import com.ttk.cinema.POJOs.Movie;
import com.ttk.cinema.exceptions.AppException;
import com.ttk.cinema.exceptions.ErrorCode;
import com.ttk.cinema.mappers.MovieMapper;
import com.ttk.cinema.repositories.MovieRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MovieService {
    MovieRepository movieRepository;
    MovieMapper movieMapper;
    CloudinaryService cloudinaryService;

    public MovieResponse createMovie(MovieCreationRequest request) throws IOException {
        Movie movie = movieMapper.toMovie(request);
        if (request.getFile() != null && !request.getFile().isEmpty()) {
            movie.setMovieImage(cloudinaryService.uploadFile(request.getFile()));
        }
        return movieMapper.toMovieResponse(movieRepository.save(movie));
    }

    public MovieResponse getMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_FOUND));
        return movieMapper.toMovieResponse(movie);
    }

    public List<MovieResponse> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(movieMapper::toMovieResponse)
                .toList();
    }

    public void deleteMovie(Long movieId) {
        if (!movieRepository.existsById(movieId)) {
            throw new AppException(ErrorCode.MOVIE_NOT_FOUND);
        }
        movieRepository.deleteById(movieId);
    }

    public MovieResponse updateMovie(Long movieId, MovieUpdateRequest request) throws IOException {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_FOUND));
        movieMapper.updateMovie(movie, request);
        if (request.getFile() != null && !request.getFile().isEmpty()) {
            movie.setMovieImage(cloudinaryService.uploadFile(request.getFile()));
        }
        return movieMapper.toMovieResponse(movieRepository.save(movie));
    }
}

