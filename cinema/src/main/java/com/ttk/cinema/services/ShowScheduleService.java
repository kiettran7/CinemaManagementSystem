package com.ttk.cinema.services;

import com.ttk.cinema.DTOs.request.creation.ShowScheduleCreationRequest;
import com.ttk.cinema.DTOs.request.update.ShowScheduleUpdateRequest;
import com.ttk.cinema.DTOs.response.ShowScheduleResponse;
import com.ttk.cinema.POJOs.ShowSchedule;
import com.ttk.cinema.exceptions.AppException;
import com.ttk.cinema.exceptions.ErrorCode;
import com.ttk.cinema.mappers.ShowScheduleMapper;
import com.ttk.cinema.repositories.MovieRepository;
import com.ttk.cinema.repositories.ShowScheduleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShowScheduleService {
    ShowScheduleRepository showScheduleRepository;
    ShowScheduleMapper showScheduleMapper;
    MovieRepository movieRepository;

    public ShowScheduleResponse createShowSchedule(ShowScheduleCreationRequest request) {
        ShowSchedule showSchedule = showScheduleMapper.toShowSchedule(request);

        // Kiểm tra movieId có tồn tại không
        movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_FOUND));

        showSchedule = showScheduleRepository.save(showSchedule);
        return showScheduleMapper.toShowScheduleResponse(showSchedule);
    }

    public ShowScheduleResponse getShowSchedule(Long showScheduleId) {
        ShowSchedule showSchedule = showScheduleRepository.findById(showScheduleId)
                .orElseThrow(() -> new AppException(ErrorCode.SHOW_SCHEDULE_NOT_FOUND));
        return showScheduleMapper.toShowScheduleResponse(showSchedule);
    }

    public List<ShowScheduleResponse> getAllShowSchedules() {
        return showScheduleRepository.findAll().stream()
                .map(showScheduleMapper::toShowScheduleResponse)
                .toList();
    }

    public void deleteShowSchedule(Long showScheduleId) {
        if (!showScheduleRepository.existsById(showScheduleId)) {
            throw new AppException(ErrorCode.SHOW_SCHEDULE_NOT_FOUND);
        }
        showScheduleRepository.deleteById(showScheduleId);
    }

    public ShowScheduleResponse updateShowSchedule(Long showScheduleId, ShowScheduleUpdateRequest request) {
        ShowSchedule showSchedule = showScheduleRepository.findById(showScheduleId)
                .orElseThrow(() -> new AppException(ErrorCode.SHOW_SCHEDULE_NOT_FOUND));
        showScheduleMapper.updateShowSchedule(showSchedule, request);
        showSchedule = showScheduleRepository.save(showSchedule);
        return showScheduleMapper.toShowScheduleResponse(showSchedule);
    }
}