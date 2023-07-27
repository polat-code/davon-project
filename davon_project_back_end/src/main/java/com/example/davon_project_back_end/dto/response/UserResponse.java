package com.example.davon_project_back_end.dto.response;

import jakarta.persistence.Column;


public record UserResponse(
        Long userId,
        String name,
        String surname,
        String telephone,
        String studentNumber
) {
}
