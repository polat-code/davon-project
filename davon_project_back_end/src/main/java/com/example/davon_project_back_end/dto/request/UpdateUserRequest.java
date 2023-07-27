package com.example.davon_project_back_end.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UpdateUserRequest {
    private Long id;
    private String name;
    private String surname;
    private String telephone;
    private String studentNumber;
}
