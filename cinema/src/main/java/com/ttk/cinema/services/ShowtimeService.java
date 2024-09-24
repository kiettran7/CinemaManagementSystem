package com.ttk.cinema.services;

import com.ttk.cinema.DTOs.request.creation.MovieCreationRequest;
import com.ttk.cinema.DTOs.request.creation.ShowtimeCreationRequest;
import com.ttk.cinema.DTOs.request.update.MovieUpdateRequest;
import com.ttk.cinema.DTOs.request.update.ShowtimeUpdateRequest;
import com.ttk.cinema.DTOs.response.MovieResponse;
import com.ttk.cinema.DTOs.response.ShowtimeResponse;
import com.ttk.cinema.POJOs.Movie;
import com.ttk.cinema.POJOs.Showtime;
import com.ttk.cinema.exceptions.AppException;
import com.ttk.cinema.exceptions.ErrorCode;
import com.ttk.cinema.mappers.MovieMapper;
import com.ttk.cinema.mappers.ShowtimeMapper;
import com.ttk.cinema.repositories.MovieRepository;
import com.ttk.cinema.repositories.ShowtimeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShowtimeService {
    ShowtimeRepository showtimeRepository;
    ShowtimeMapper showtimeMapper;

    public ShowtimeResponse createShowtime(ShowtimeCreationRequest request) {
        Showtime showtime = showtimeMapper.toShowtime(request);
        showtime = showtimeRepository.save(showtime);
        return showtimeMapper.toShowtimeResponse(showtime);
    }

    public ShowtimeResponse getShowtime(Long showtimeId) {
        Showtime showtime = showtimeRepository.findById(showtimeId)
                .orElseThrow(() -> new AppException(ErrorCode.SHOWTIME_NOT_FOUND));
        return showtimeMapper.toShowtimeResponse(showtime);
    }

    public List<ShowtimeResponse> getAllShowtimes() {
        return showtimeRepository.findAll().stream()
                .map(showtimeMapper::toShowtimeResponse)
                .toList();
    }

    public void deleteShowtime(Long showtimeId) {
        if (!showtimeRepository.existsById(showtimeId)) {
            throw new AppException(ErrorCode.SHOWTIME_NOT_FOUND);
        }
        showtimeRepository.deleteById(showtimeId);
    }

    public ShowtimeResponse updateShowtime(Long showtimeId, ShowtimeUpdateRequest request) {
        Showtime showtime = showtimeRepository.findById(showtimeId)
                .orElseThrow(() -> new AppException(ErrorCode.SHOWTIME_NOT_FOUND));
        showtimeMapper.updateShowtime(showtime, request);
        showtime = showtimeRepository.save(showtime);
        return showtimeMapper.toShowtimeResponse(showtime);
    }
}