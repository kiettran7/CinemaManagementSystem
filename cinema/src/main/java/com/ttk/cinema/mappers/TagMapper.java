package com.ttk.cinema.mappers;

import com.ttk.cinema.DTOs.request.creation.TagCreationRequest;
import com.ttk.cinema.DTOs.request.update.TagUpdateRequest;
import com.ttk.cinema.DTOs.response.TagResponse;
import com.ttk.cinema.POJOs.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TagMapper {
    Tag toTag(TagCreationRequest request);
    TagResponse toTagResponse(Tag tag);

    @Mapping(target = "movies", ignore = true)
    void updateTag(@MappingTarget Tag tag, TagUpdateRequest request);
}
