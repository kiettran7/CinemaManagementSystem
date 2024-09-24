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

    @Mapping(source = "show.showId", target = "show.showId")

    @Mapping(source = "seat.seatId", target = "seat.seatId")
    @Mapping(source = "seat.seatName", target = "seat.seatName")

    @Mapping(source = "customer.userId", target = "customer.userId")
    @Mapping(source = "customer.username", target = "customer.username")
    @Mapping(source = "customer.email", target = "customer.email")
    @Mapping(source = "customer.phone", target = "customer.phone")
    @Mapping(source = "customer.fullName", target = "customer.fullName")
    @Mapping(source = "customer.birthday", target = "customer.birthday")
    @Mapping(source = "customer.joinedDate", target = "customer.joinedDate")
    @Mapping(source = "customer.avatar", target = "customer.avatar")


    @Mapping(source = "staff.userId", target = "staff.userId")
    @Mapping(source = "staff.username", target = "staff.username")
    @Mapping(source = "staff.email", target = "staff.email")
    @Mapping(source = "staff.phone", target = "staff.phone")
    @Mapping(source = "staff.fullName", target = "staff.fullName")
    @Mapping(source = "staff.birthday", target = "staff.birthday")
    @Mapping(source = "staff.joinedDate", target = "staff.joinedDate")
    @Mapping(source = "staff.avatar", target = "staff.avatar")
    TicketResponse toTicketResponse(Ticket ticket);

    @Mapping(target = "show", ignore = true)
    void updateTicket(@MappingTarget Ticket ticket, TicketUpdateRequest request);
}
