package com.ttk.cinema.controllers;

import com.ttk.cinema.DTOs.request.ApiResponse;
import com.ttk.cinema.DTOs.request.creation.GenreCreationRequest;
import com.ttk.cinema.DTOs.request.creation.TagCreationRequest;
import com.ttk.cinema.DTOs.request.update.GenreUpdateRequest;
import com.ttk.cinema.DTOs.request.update.TagUpdateRequest;
import com.ttk.cinema.DTOs.response.GenreResponse;
import com.ttk.cinema.DTOs.response.TagResponse;
import com.ttk.cinema.services.GenreService;
import com.ttk.cinema.services.TagService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TagController {
    TagService tagService;

    @PostMapping
    ApiResponse<TagResponse> createTag(@RequestBody TagCreationRequest request) {
        return ApiResponse.<TagResponse>builder()
                .result(tagService.createTag(request))
                .build();
    }

    @GetMapping("/{tagId}")
    ApiResponse<TagResponse> getTag(@PathVariable Long tagId) {
        return ApiResponse.<TagResponse>builder()
                .result(tagService.getTag(tagId))
                .build();
    }

    @GetMapping
    ApiResponse<List<TagResponse>> getAllTags() {
        return ApiResponse.<List<TagResponse>>builder()
                .result(tagService.getAllTags())
                .build();
    }

    @PutMapping("/{tagId}")
    ApiResponse<TagResponse> updateTag(@PathVariable Long tagId,
                                       @RequestBody TagUpdateRequest request) {
        return ApiResponse.<TagResponse>builder()
                .result(tagService.updateTag(tagId, request))
                .build();
    }

    @DeleteMapping("/{tagId}")
    ApiResponse<Void> deleteTag(@PathVariable Long tagId) {
        tagService.deleteTag(tagId);
        return ApiResponse.<Void>builder().build();
    }
}