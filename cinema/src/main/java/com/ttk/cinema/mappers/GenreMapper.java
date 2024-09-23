package com.ttk.cinema.mappers;

import com.ttk.cinema.DTOs.request.creation.GenreCreationRequest;
import com.ttk.cinema.DTOs.request.update.GenreUpdateRequest;
import com.ttk.cinema.DTOs.response.GenreResponse;
import com.ttk.cinema.POJOs.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    Genre toGenre(GenreCreationRequest request);
    GenreResponse toGenreResponse(Genre genre);

    @Mapping(target = "movies", ignore = true)
    void updateGenre(@MappingTarget Genre genre, GenreUpdateRequest request);
}
