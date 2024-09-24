package com.ttk.cinema.controllers;

import com.ttk.cinema.DTOs.request.ApiResponse;
import com.ttk.cinema.DTOs.request.creation.UserCreationRequest;
import com.ttk.cinema.DTOs.request.update.UserUpdateRequest;
import com.ttk.cinema.DTOs.response.UserResponse;
import com.ttk.cinema.POJOs.User;
import com.ttk.cinema.services.CloudinaryService;
import com.ttk.cinema.services.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    UserService userService;
    CloudinaryService cloudinaryService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@ModelAttribute UserCreationRequest request) throws IOException {
        logger.info("POST request to /users");

        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request, true))
                .build();
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getUsers() {
        logger.info("GET request to /users");
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable("userId") String userId) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUser(Long.parseLong(userId)))
                .build();
    }

    @GetMapping("/my-info")
    ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable String userId,
                                         @ModelAttribute UserUpdateRequest request) throws IOException {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(Long.parseLong(userId), request))
                .build();
    }

    @DeleteMapping("/{userId}")
    ApiResponse<String> deleteUser(@PathVariable String userId) {
        userService.deleteUser(Long.parseLong(userId));
        return ApiResponse.<String>builder()
                .result("User has been deleted")
                .build();
    }
}
