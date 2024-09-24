package com.ttk.cinema.services;

import com.ttk.cinema.DTOs.request.creation.MovieCreationRequest;
import com.ttk.cinema.DTOs.request.creation.SeatCreationRequest;
import com.ttk.cinema.DTOs.request.update.MovieUpdateRequest;
import com.ttk.cinema.DTOs.request.update.SeatUpdateRequest;
import com.ttk.cinema.DTOs.response.MovieResponse;
import com.ttk.cinema.DTOs.response.SeatResponse;
import com.ttk.cinema.POJOs.Movie;
import com.ttk.cinema.POJOs.Seat;
import com.ttk.cinema.exceptions.AppException;
import com.ttk.cinema.exceptions.ErrorCode;
import com.ttk.cinema.mappers.MovieMapper;
import com.ttk.cinema.mappers.SeatMapper;
import com.ttk.cinema.repositories.MovieRepository;
import com.ttk.cinema.repositories.SeatRepository;
import com.ttk.cinema.repositories.ShowRoomRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SeatService {
    SeatRepository seatRepository;
    SeatMapper seatMapper;
    ShowRoomRepository showRoomRepository;

    public SeatResponse createSeat(SeatCreationRequest request) {
        // Kiểm tra showRoomId có tồn tại không
        showRoomRepository.findById(request.getShowRoomId())
                .orElseThrow(() -> new AppException(ErrorCode.SHOW_ROOM_NOT_FOUND));

        Seat seat = seatMapper.toSeat(request);
        seat = seatRepository.save(seat);
        return seatMapper.toSeatResponse(seat);
    }

    public SeatResponse getSeat(Long seatId) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new AppException(ErrorCode.SEAT_NOT_FOUND));
        return seatMapper.toSeatResponse(seat);
    }

    public List<SeatResponse> getAllSeats() {
        return seatRepository.findAll().stream()
                .map(seatMapper::toSeatResponse)
                .toList();
    }

    public void deleteSeat(Long seatId) {
        if (!seatRepository.existsById(seatId)) {
            throw new AppException(ErrorCode.SEAT_NOT_FOUND);
        }
        seatRepository.deleteById(seatId);
    }

    public SeatResponse updateSeat(Long seatId, SeatUpdateRequest request) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new AppException(ErrorCode.SEAT_NOT_FOUND));

        seatMapper.updateSeat(seat, request);
        seat = seatRepository.save(seat);
        return seatMapper.toSeatResponse(seat);
    }
}