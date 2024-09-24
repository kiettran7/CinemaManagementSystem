package com.ttk.cinema.controllers;

import com.ttk.cinema.DTOs.request.ApiResponse;
import com.ttk.cinema.DTOs.request.creation.ShowScheduleCreationRequest;
import com.ttk.cinema.DTOs.request.creation.UserCreationRequest;
import com.ttk.cinema.DTOs.request.update.ShowScheduleUpdateRequest;
import com.ttk.cinema.DTOs.request.update.UserUpdateRequest;
import com.ttk.cinema.DTOs.response.ShowScheduleResponse;
import com.ttk.cinema.DTOs.response.UserResponse;
import com.ttk.cinema.POJOs.User;
import com.ttk.cinema.services.CloudinaryService;
import com.ttk.cinema.services.ShowScheduleService;
import com.ttk.cinema.services.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/show-schedules")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShowScheduleController {
    ShowScheduleService showScheduleService;

    @PostMapping
    ApiResponse<ShowScheduleResponse> createShowSchedule(@RequestBody ShowScheduleCreationRequest request) {
        return ApiResponse.<ShowScheduleResponse>builder()
                .result(showScheduleService.createShowSchedule(request))
                .build();
    }

    @GetMapping("/{showScheduleId}")
    ApiResponse<ShowScheduleResponse> getShowSchedule(@PathVariable Long showScheduleId) {
        return ApiResponse.<ShowScheduleResponse>builder()
                .result(showScheduleService.getShowSchedule(showScheduleId))
                .build();
    }

    @GetMapping
    ApiResponse<List<ShowScheduleResponse>> getAllShowSchedules() {
        return ApiResponse.<List<ShowScheduleResponse>>builder()
                .result(showScheduleService.getAllShowSchedules())
                .build();
    }

    @PutMapping("/{showScheduleId}")
    ApiResponse<ShowScheduleResponse> updateShowSchedule(@PathVariable Long showScheduleId,
                                                         @RequestBody ShowScheduleUpdateRequest request) {
        return ApiResponse.<ShowScheduleResponse>builder()
                .result(showScheduleService.updateShowSchedule(showScheduleId, request))
                .build();
    }

    @DeleteMapping("/{showScheduleId}")
    ApiResponse<Void> deleteShowSchedule(@PathVariable Long showScheduleId) {
        showScheduleService.deleteShowSchedule(showScheduleId);
        return ApiResponse.<Void>builder().build();
    }
}