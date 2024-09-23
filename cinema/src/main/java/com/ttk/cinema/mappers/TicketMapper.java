package com.ttk.cinema.mappers;

import com.ttk.cinema.DTOs.request.creation.TicketCreationRequest;
import com.ttk.cinema.DTOs.request.update.TicketUpdateRequest;
import com.ttk.cinema.DTOs.response.TicketResponse;
import com.ttk.cinema.POJOs.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    Ticket toTicket(TicketCreationRequest request);
    TicketResponse toTicketResponse(Ticket ticket);

    @Mapping(target = "showEvent", ignore = true)
    void updateTicket(@MappingTarget Ticket ticket, TicketUpdateRequest request);
}
