package com.example.davon_project_back_end.controller;

import com.example.davon_project_back_end.dto.request.AddUserRequest;
import com.example.davon_project_back_end.dto.request.UpdateUserRequest;
import com.example.davon_project_back_end.dto.response.UserResponse;
import com.example.davon_project_back_end.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/add-user")
    public ResponseEntity<String> addNewUser(@RequestBody AddUserRequest addUserRequest){
        return userService.addNewUser(addUserRequest);
    }

    @PutMapping("/update-user")
    public ResponseEntity<String> updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        return userService.updateUser(updateUserRequest);
    }


}
