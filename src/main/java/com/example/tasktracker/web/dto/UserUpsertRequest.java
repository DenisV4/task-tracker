package com.example.tasktracker.web.dto;

import com.example.tasktracker.model.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpsertRequest {

    private String username;

    private String email;

    private String password;

    private Set<RoleType> roles;
}
