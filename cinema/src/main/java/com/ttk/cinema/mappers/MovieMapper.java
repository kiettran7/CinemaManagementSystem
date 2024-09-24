package com.ttk.cinema.mappers;

import com.ttk.cinema.DTOs.request.creation.MovieCreationRequest;
import com.ttk.cinema.DTOs.request.update.MovieUpdateRequest;
import com.ttk.cinema.DTOs.response.MovieResponse;
import com.ttk.cinema.POJOs.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "tags", ignore = true)
    Movie toMovie(MovieCreationRequest request);

    MovieResponse toMovieResponse(Movie movie);

    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "tags", ignore = true)
    void updateMovie(@MappingTarget Movie movie, MovieUpdateRequest request);
}
