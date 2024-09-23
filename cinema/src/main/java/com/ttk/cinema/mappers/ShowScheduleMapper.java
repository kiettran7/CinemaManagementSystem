package com.ttk.cinema.mappers;

import com.ttk.cinema.DTOs.request.creation.ShowScheduleCreationRequest;
import com.ttk.cinema.DTOs.request.update.ShowScheduleUpdateRequest;
import com.ttk.cinema.DTOs.response.ShowScheduleResponse;
import com.ttk.cinema.POJOs.ShowSchedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ShowScheduleMapper {
    ShowSchedule toShowSchedule(ShowScheduleCreationRequest request);
    ShowScheduleResponse toShowScheduleResponse(ShowSchedule showSchedule);

    @Mapping(target = "movie", ignore = true)
    void updateShowSchedule(@MappingTarget ShowSchedule showSchedule, ShowScheduleUpdateRequest request);
}
