package com.ttk.cinema.mappers;

import com.ttk.cinema.DTOs.request.creation.ShowRoomCreationRequest;
import com.ttk.cinema.DTOs.request.update.ShowRoomUpdateRequest;
import com.ttk.cinema.DTOs.response.ShowRoomResponse;
import com.ttk.cinema.POJOs.ShowRoom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ShowRoomMapper {
    ShowRoom toShowRoom(ShowRoomCreationRequest request);
    ShowRoomResponse toShowRoomResponse(ShowRoom showRoom);

    @Mapping(target = "seats", ignore = true)
    void updateShowRoom(@MappingTarget ShowRoom showRoom, ShowRoomUpdateRequest request);
}
