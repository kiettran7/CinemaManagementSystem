package com.ttk.cinema.services;

import com.ttk.cinema.DTOs.request.creation.MovieCreationRequest;
import com.ttk.cinema.DTOs.request.creation.TicketCreationRequest;
import com.ttk.cinema.DTOs.request.update.MovieUpdateRequest;
import com.ttk.cinema.DTOs.request.update.TicketUpdateRequest;
import com.ttk.cinema.DTOs.response.MovieResponse;
import com.ttk.cinema.DTOs.response.TicketResponse;
import com.ttk.cinema.POJOs.Movie;
import com.ttk.cinema.POJOs.Ticket;
import com.ttk.cinema.exceptions.AppException;
import com.ttk.cinema.exceptions.ErrorCode;
import com.ttk.cinema.mappers.MovieMapper;
import com.ttk.cinema.mappers.TicketMapper;
import com.ttk.cinema.repositories.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TicketService {
    TicketRepository ticketRepository;
    TicketMapper ticketMapper;
    ShowEventRepository showEventRepository;
    SeatRepository seatRepository;
    UserRepository userRepository;
    MovieRepository movieRepository;

    public TicketResponse createTicket(TicketCreationRequest request) {
        // Kiểm tra các liên kết có tồn tại không
        showEventRepository.findById(request.getShowId())
                .orElseThrow(() -> new AppException(ErrorCode.SHOW_EVENT_NOT_FOUND));

        seatRepository.findById(request.getSeatId())
                .orElseThrow(() -> new AppException(ErrorCode.SEAT_NOT_FOUND));

        userRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userRepository.findById(request.getStaffId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_FOUND));

        Ticket ticket = ticketMapper.toTicket(request);
        ticket = ticketRepository.save(ticket);
        return ticketMapper.toTicketResponse(ticket);
    }

    public TicketResponse getTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new AppException(ErrorCode.TICKET_NOT_FOUND));
        return ticketMapper.toTicketResponse(ticket);
    }

    public List<TicketResponse> getAllTickets() {
        return ticketRepository.findAll().stream()
                .map(ticketMapper::toTicketResponse)
                .toList();
    }

    public void deleteTicket(Long ticketId) {
        if (!ticketRepository.existsById(ticketId)) {
            throw new AppException(ErrorCode.TICKET_NOT_FOUND);
        }
        ticketRepository.deleteById(ticketId);
    }

    public TicketResponse updateTicket(Long ticketId, TicketUpdateRequest request) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new AppException(ErrorCode.TICKET_NOT_FOUND));

        ticketMapper.updateTicket(ticket, request);
        ticket = ticketRepository.save(ticket);
        return ticketMapper.toTicketResponse(ticket);
    }
}