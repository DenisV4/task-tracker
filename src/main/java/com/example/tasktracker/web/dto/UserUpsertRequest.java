package com.example.tasktracker.web.dto;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpsertRequest {

    private String username;

    private String email;
}
