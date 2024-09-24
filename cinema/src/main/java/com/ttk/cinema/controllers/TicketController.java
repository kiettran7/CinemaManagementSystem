package com.ttk.cinema.controllers;

import com.ttk.cinema.DTOs.request.ApiResponse;
import com.ttk.cinema.DTOs.request.creation.MovieCreationRequest;
import com.ttk.cinema.DTOs.request.creation.TicketCreationRequest;
import com.ttk.cinema.DTOs.request.update.MovieUpdateRequest;
import com.ttk.cinema.DTOs.request.update.TicketUpdateRequest;
import com.ttk.cinema.DTOs.response.MovieResponse;
import com.ttk.cinema.DTOs.response.TicketResponse;
import com.ttk.cinema.services.MovieService;
import com.ttk.cinema.services.TicketService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TicketController {
    TicketService ticketService;

    @PostMapping
    ApiResponse<TicketResponse> createTicket(@RequestBody TicketCreationRequest request) {
        return ApiResponse.<TicketResponse>builder()
                .result(ticketService.createTicket(request))
                .build();
    }

    @GetMapping("/{ticketId}")
    ApiResponse<TicketResponse> getTicket(@PathVariable Long ticketId) {
        return ApiResponse.<TicketResponse>builder()
                .result(ticketService.getTicket(ticketId))
                .build();
    }

    @GetMapping
    ApiResponse<List<TicketResponse>> getAllTickets() {
        return ApiResponse.<List<TicketResponse>>builder()
                .result(ticketService.getAllTickets())
                .build();
    }

    @PutMapping("/{ticketId}")
    ApiResponse<TicketResponse> updateTicket(@PathVariable Long ticketId,
                                             @RequestBody TicketUpdateRequest request) {
        return ApiResponse.<TicketResponse>builder()
                .result(ticketService.updateTicket(ticketId, request))
                .build();
    }

    @DeleteMapping("/{ticketId}")
    ApiResponse<Void> deleteTicket(@PathVariable Long ticketId) {
        ticketService.deleteTicket(ticketId);
        return ApiResponse.<Void>builder().build();
    }
}