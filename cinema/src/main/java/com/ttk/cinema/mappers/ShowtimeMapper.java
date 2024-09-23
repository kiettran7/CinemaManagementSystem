package com.ttk.cinema.mappers;

import com.ttk.cinema.DTOs.request.creation.ShowtimeCreationRequest;
import com.ttk.cinema.DTOs.request.update.ShowtimeUpdateRequest;
import com.ttk.cinema.DTOs.response.ShowtimeResponse;
import com.ttk.cinema.POJOs.Showtime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ShowtimeMapper {
    Showtime toShowtime(ShowtimeCreationRequest request);
    ShowtimeResponse toShowtimeResponse(Showtime showtime);

    @Mapping(target = "showEvents", ignore = true)
    void updateShowtime(@MappingTarget Showtime showtime, ShowtimeUpdateRequest request);
}
