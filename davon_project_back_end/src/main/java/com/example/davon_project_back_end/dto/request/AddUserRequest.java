package com.example.davon_project_back_end.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AddUserRequest {

    public AddUserRequest() {

    }

    private String name;
    private String surname;
    private String telephone;
    private String studentNumber;

}
