package com.ttk.cinema.services;

import com.ttk.cinema.DTOs.request.ShowRoomRequest;
import com.ttk.cinema.DTOs.response.ShowRoomResponse;
import com.ttk.cinema.POJOs.ShowRoom;
import com.ttk.cinema.exceptions.AppException;
import com.ttk.cinema.exceptions.ErrorCode;
import com.ttk.cinema.mappers.ShowRoomMapper;
import com.ttk.cinema.repositories.ShowRoomRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShowRoomService {
    ShowRoomRepository showRoomRepository;
    ShowRoomMapper showRoomMapper;

    @PreAuthorize("hasRole('ADMIN')")
    public ShowRoomResponse createShowRoom(ShowRoomRequest request) {
        ShowRoom showRoom = showRoomMapper.toShowRoom(request);

        return showRoomMapper.toShowRoomResponse(showRoomRepository.save(showRoom));
    }

    public ShowRoomResponse getShowRoom(String showRoomId) {
        ShowRoom showRoom = showRoomRepository.findById(showRoomId)
                .orElseThrow(() -> new AppException(ErrorCode.SHOW_ROOM_NOT_FOUND));
        return showRoomMapper.toShowRoomResponse(showRoom);
    }

    public List<ShowRoomResponse> getAllShowRooms() {
        return showRoomRepository.findAll().stream()
                .map(showRoomMapper::toShowRoomResponse)
                .toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteShowRoom(String showRoomId) {
        if (!showRoomRepository.existsById(showRoomId)) {
            throw new AppException(ErrorCode.SHOW_ROOM_NOT_FOUND);
        }
        showRoomRepository.deleteById(showRoomId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ShowRoomResponse updateShowRoom(String showRoomId, ShowRoomRequest request) {
        ShowRoom showRoom = showRoomRepository.findById(showRoomId)
                .orElseThrow(() -> new AppException(ErrorCode.SHOW_ROOM_NOT_FOUND));
        showRoomMapper.updateShowRoom(showRoom, request);

        return showRoomMapper.toShowRoomResponse(showRoomRepository.save(showRoom));
    }
}