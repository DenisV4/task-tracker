package com.example.tasktracker.mapper;

import com.example.tasktracker.model.User;
import com.example.tasktracker.web.dto.UserResponse;
import com.example.tasktracker.web.dto.UserUpsertRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper {

    private PasswordEncoder passwordEncoder;

    @Mapping(target = "password", expression = "java(encode(request))")
    public abstract User requestToUser(UserUpsertRequest request);

    public abstract User requestToUser(String id, UserUpsertRequest request);

    public abstract UserResponse userToResponse(User user);

    public abstract Set<UserResponse> userSetToUserResponseSet(Set<User> users);

    protected String encode(UserUpsertRequest request) {
        return passwordEncoder.encode(request.getPassword());
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
