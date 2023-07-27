package com.example.davon_project_back_end.service;

import com.example.davon_project_back_end.dto.request.AddUserRequest;
import com.example.davon_project_back_end.dto.response.UserResponse;
import com.example.davon_project_back_end.model.User;
import com.example.davon_project_back_end.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;


    public ResponseEntity<List<UserResponse>> getAllUsers() {

        List<User> users = userRepository.findAll();
        List<UserResponse> userResponseList = new ArrayList<>();

        users.forEach((user -> {userResponseList.add(
                                        new UserResponse(
                                                user.getUserId(),
                                                user.getName(),
                                                user.getSurname(),
                                                user.getTelephone(),
                                                user.getStudentNumber()));}));
        return new ResponseEntity<>(userResponseList, HttpStatus.OK);

    }

    public ResponseEntity<String> addNewUser(AddUserRequest addUserRequest) {
        Optional<User> optionalUser = userRepository.findByStudentNumber(addUserRequest.getStudentNumber());
        if(optionalUser.isPresent()){
            return new ResponseEntity<>("Already used student number",HttpStatus.NOT_ACCEPTABLE);
        }
        User user = User.builder()
                .name(addUserRequest.getName())
                .surname(addUserRequest.getSurname())
                .telephone(addUserRequest.getTelephone())
                .studentNumber(addUserRequest.getStudentNumber())
                .build();
        userRepository.save(user);


        return new ResponseEntity<>("User is added successfully!",HttpStatus.OK);

    }
}
