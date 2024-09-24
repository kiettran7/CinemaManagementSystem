package com.ttk.cinema.controllers;

import com.ttk.cinema.DTOs.request.ApiResponse;
import com.ttk.cinema.DTOs.request.creation.GenreCreationRequest;
import com.ttk.cinema.DTOs.request.creation.SeatCreationRequest;
import com.ttk.cinema.DTOs.request.update.GenreUpdateRequest;
import com.ttk.cinema.DTOs.request.update.SeatUpdateRequest;
import com.ttk.cinema.DTOs.response.GenreResponse;
import com.ttk.cinema.DTOs.response.SeatResponse;
import com.ttk.cinema.services.GenreService;
import com.ttk.cinema.services.SeatService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SeatController {
    SeatService seatService;

    @PostMapping
    ApiResponse<SeatResponse> createSeat(@RequestBody SeatCreationRequest request) {
        return ApiResponse.<SeatResponse>builder()
                .result(seatService.createSeat(request))
                .build();
    }

    @GetMapping("/{seatId}")
    ApiResponse<SeatResponse> getSeat(@PathVariable Long seatId) {
        return ApiResponse.<SeatResponse>builder()
                .result(seatService.getSeat(seatId))
                .build();
    }

    @GetMapping
    ApiResponse<List<SeatResponse>> getAllSeats() {
        return ApiResponse.<List<SeatResponse>>builder()
                .result(seatService.getAllSeats())
                .build();
    }

    @PutMapping("/{seatId}")
    ApiResponse<SeatResponse> updateSeat(@PathVariable Long seatId,
                                         @RequestBody SeatUpdateRequest request) {
        return ApiResponse.<SeatResponse>builder()
                .result(seatService.updateSeat(seatId, request))
                .build();
    }

    @DeleteMapping("/{seatId}")
    ApiResponse<Void> deleteSeat(@PathVariable Long seatId) {
        seatService.deleteSeat(seatId);
        return ApiResponse.<Void>builder().build();
    }
}