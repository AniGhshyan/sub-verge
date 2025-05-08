package com.example.subverge.mapper;

import com.example.subverge.dto.request.UserRequest;
import com.example.subverge.dto.response.UserResponse;
import com.example.subverge.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public abstract User mapToEntity(UserRequest request);

    public abstract UserResponse toResponse(User user);

    @Mapping(target = "id", ignore = true)
    public abstract void updateEntity(@MappingTarget User user, UserRequest request);
}
