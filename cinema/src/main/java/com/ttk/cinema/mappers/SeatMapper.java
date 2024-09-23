package com.ttk.cinema.mappers;

import com.ttk.cinema.DTOs.request.creation.SeatCreationRequest;
import com.ttk.cinema.DTOs.request.update.SeatUpdateRequest;
import com.ttk.cinema.DTOs.response.SeatResponse;
import com.ttk.cinema.POJOs.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SeatMapper {
    Seat toSeat(SeatCreationRequest request);
    SeatResponse toSeatResponse(Seat seat);

    @Mapping(target = "reservations", ignore = true)
    void updateSeat(@MappingTarget Seat seat, SeatUpdateRequest request);
}
