package com.ttk.cinema.mappers;

import com.ttk.cinema.DTOs.request.UserCreationRequest;
import com.ttk.cinema.DTOs.request.UserUpdateRequest;
import com.ttk.cinema.DTOs.response.UserResponse;
import com.ttk.cinema.POJOs.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

//    @Mapping(source = "firstName", target = "lastName") trùng nhau
//@Mapping(target = "firstName", source = "firstName", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
