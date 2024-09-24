package com.ttk.cinema.controllers;

import com.ttk.cinema.DTOs.request.ApiResponse;
import com.ttk.cinema.DTOs.request.creation.MovieCreationRequest;
import com.ttk.cinema.DTOs.request.creation.ShowtimeCreationRequest;
import com.ttk.cinema.DTOs.request.update.MovieUpdateRequest;
import com.ttk.cinema.DTOs.request.update.ShowtimeUpdateRequest;
import com.ttk.cinema.DTOs.response.MovieResponse;
import com.ttk.cinema.DTOs.response.ShowtimeResponse;
import com.ttk.cinema.services.MovieService;
import com.ttk.cinema.services.ShowtimeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/showtimes")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShowtimeController {
    ShowtimeService showtimeService;

    @PostMapping
    ApiResponse<ShowtimeResponse> createShowtime(@RequestBody ShowtimeCreationRequest request) {
        return ApiResponse.<ShowtimeResponse>builder()
                .result(showtimeService.createShowtime(request))
                .build();
    }

    @GetMapping("/{showtimeId}")
    ApiResponse<ShowtimeResponse> getShowtime(@PathVariable Long showtimeId) {
        return ApiResponse.<ShowtimeResponse>builder()
                .result(showtimeService.getShowtime(showtimeId))
                .build();
    }

    @GetMapping
    ApiResponse<List<ShowtimeResponse>> getAllShowtimes() {
        return ApiResponse.<List<ShowtimeResponse>>builder()
                .result(showtimeService.getAllShowtimes())
                .build();
    }

    @PutMapping("/{showtimeId}")
    ApiResponse<ShowtimeResponse> updateShowtime(@PathVariable Long showtimeId,
                                                 @RequestBody ShowtimeUpdateRequest request) {
        return ApiResponse.<ShowtimeResponse>builder()
                .result(showtimeService.updateShowtime(showtimeId, request))
                .build();
    }

    @DeleteMapping("/{showtimeId}")
    ApiResponse<Void> deleteShowtime(@PathVariable Long showtimeId) {
        showtimeService.deleteShowtime(showtimeId);
        return ApiResponse.<Void>builder().build();
    }
}