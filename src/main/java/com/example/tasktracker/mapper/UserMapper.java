package com.example.tasktracker.mapper;

import com.example.tasktracker.model.User;
import com.example.tasktracker.web.dto.UserResponse;
import com.example.tasktracker.web.dto.UserUpsertRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User requestToUser(UserUpsertRequest request);

    User requestToUser(String id, UserUpsertRequest request);

    UserResponse userToResponse(User user);

    Set<UserResponse> userSetToUserResponseSet(Set<User> users);
}
