package com.ttk.cinema.services;

import com.ttk.cinema.DTOs.request.creation.GenreCreationRequest;
import com.ttk.cinema.DTOs.request.creation.ShowRoomCreationRequest;
import com.ttk.cinema.DTOs.request.update.GenreUpdateRequest;
import com.ttk.cinema.DTOs.request.update.ShowRoomUpdateRequest;
import com.ttk.cinema.DTOs.response.GenreResponse;
import com.ttk.cinema.DTOs.response.ShowRoomResponse;
import com.ttk.cinema.POJOs.Genre;
import com.ttk.cinema.POJOs.ShowRoom;
import com.ttk.cinema.exceptions.AppException;
import com.ttk.cinema.exceptions.ErrorCode;
import com.ttk.cinema.mappers.GenreMapper;
import com.ttk.cinema.mappers.ShowRoomMapper;
import com.ttk.cinema.repositories.GenreRepository;
import com.ttk.cinema.repositories.ShowRoomRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShowRoomService {
    ShowRoomRepository showRoomRepository;
    ShowRoomMapper showRoomMapper;

    public ShowRoomResponse createShowRoom(ShowRoomCreationRequest request) {
        ShowRoom showRoom = showRoomMapper.toShowRoom(request);
        showRoom = showRoomRepository.save(showRoom);
        return showRoomMapper.toShowRoomResponse(showRoom);
    }

    public ShowRoomResponse getShowRoom(Long showRoomId) {
        ShowRoom showRoom = showRoomRepository.findById(showRoomId)
                .orElseThrow(() -> new AppException(ErrorCode.SHOW_ROOM_NOT_FOUND));
        return showRoomMapper.toShowRoomResponse(showRoom);
    }

    public List<ShowRoomResponse> getAllShowRooms() {
        return showRoomRepository.findAll().stream()
                .map(showRoomMapper::toShowRoomResponse)
                .toList();
    }

    public void deleteShowRoom(Long showRoomId) {
        if (!showRoomRepository.existsById(showRoomId)) {
            throw new AppException(ErrorCode.SHOW_ROOM_NOT_FOUND);
        }
        showRoomRepository.deleteById(showRoomId);
    }

    public ShowRoomResponse updateShowRoom(Long showRoomId, ShowRoomUpdateRequest request) {
        ShowRoom showRoom = showRoomRepository.findById(showRoomId)
                .orElseThrow(() -> new AppException(ErrorCode.SHOW_ROOM_NOT_FOUND));
        showRoomMapper.updateShowRoom(showRoom, request);
        showRoom = showRoomRepository.save(showRoom);
        return showRoomMapper.toShowRoomResponse(showRoom);
    }
}