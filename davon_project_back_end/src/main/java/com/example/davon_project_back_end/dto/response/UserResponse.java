package com.example.davon_project_back_end.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public record UserResponse(
        Long userId,
        String name,
        String surname,
        String telephone,
        String studentNumber
) {
}
