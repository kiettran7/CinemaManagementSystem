package com.ttk.cinema.services;

import com.ttk.cinema.DTOs.request.creation.MovieCreationRequest;
import com.ttk.cinema.DTOs.request.creation.TagCreationRequest;
import com.ttk.cinema.DTOs.request.update.MovieUpdateRequest;
import com.ttk.cinema.DTOs.request.update.TagUpdateRequest;
import com.ttk.cinema.DTOs.response.MovieResponse;
import com.ttk.cinema.DTOs.response.TagResponse;
import com.ttk.cinema.POJOs.Movie;
import com.ttk.cinema.POJOs.Tag;
import com.ttk.cinema.exceptions.AppException;
import com.ttk.cinema.exceptions.ErrorCode;
import com.ttk.cinema.mappers.MovieMapper;
import com.ttk.cinema.mappers.TagMapper;
import com.ttk.cinema.repositories.MovieRepository;
import com.ttk.cinema.repositories.TagRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TagService {
    TagRepository tagRepository;
    TagMapper tagMapper;

    public TagResponse createTag(TagCreationRequest request) {
        Tag tag = tagMapper.toTag(request);
        tag = tagRepository.save(tag);
        return tagMapper.toTagResponse(tag);
    }

    public TagResponse getTag(Long tagId) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new AppException(ErrorCode.TAG_NOT_FOUND));
        return tagMapper.toTagResponse(tag);
    }

    public List<TagResponse> getAllTags() {
        return tagRepository.findAll().stream()
                .map(tagMapper::toTagResponse)
                .toList();
    }

    public void deleteTag(Long tagId) {
        if (!tagRepository.existsById(tagId)) {
            throw new AppException(ErrorCode.TAG_NOT_FOUND);
        }
        tagRepository.deleteById(tagId);
    }

    public TagResponse updateTag(Long tagId, TagUpdateRequest request) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new AppException(ErrorCode.TAG_NOT_FOUND));
        tagMapper.updateTag(tag, request);
        tag = tagRepository.save(tag);
        return tagMapper.toTagResponse(tag);
    }
}