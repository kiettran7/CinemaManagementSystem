package com.ttk.cinema.services;

import com.ttk.cinema.DTOs.request.creation.GenreCreationRequest;
import com.ttk.cinema.DTOs.request.creation.MovieCreationRequest;
import com.ttk.cinema.DTOs.request.update.GenreUpdateRequest;
import com.ttk.cinema.DTOs.request.update.MovieUpdateRequest;
import com.ttk.cinema.DTOs.response.GenreResponse;
import com.ttk.cinema.DTOs.response.MovieResponse;
import com.ttk.cinema.POJOs.Genre;
import com.ttk.cinema.POJOs.Movie;
import com.ttk.cinema.exceptions.AppException;
import com.ttk.cinema.exceptions.ErrorCode;
import com.ttk.cinema.mappers.GenreMapper;
import com.ttk.cinema.mappers.MovieMapper;
import com.ttk.cinema.repositories.GenreRepository;
import com.ttk.cinema.repositories.MovieRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenreService {
    GenreRepository genreRepository;
    GenreMapper genreMapper;

    public GenreResponse createGenre(GenreCreationRequest request) {
        Genre genre = genreMapper.toGenre(request);
        genre = genreRepository.save(genre);
        return genreMapper.toGenreResponse(genre);
    }

    public GenreResponse getGenre(Long genreId) {
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new AppException(ErrorCode.GENRE_NOT_FOUND));
        return genreMapper.toGenreResponse(genre);
    }

    public List<GenreResponse> getAllGenres() {
        return genreRepository.findAll().stream()
                .map(genreMapper::toGenreResponse)
                .toList();
    }

    public void deleteGenre(Long genreId) {
        if (!genreRepository.existsById(genreId)) {
            throw new AppException(ErrorCode.GENRE_NOT_FOUND);
        }
        genreRepository.deleteById(genreId);
    }

    public GenreResponse updateGenre(Long genreId, GenreUpdateRequest request) {
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new AppException(ErrorCode.GENRE_NOT_FOUND));
        genreMapper.updateGenre(genre, request);
        genre = genreRepository.save(genre);
        return genreMapper.toGenreResponse(genre);
    }
}