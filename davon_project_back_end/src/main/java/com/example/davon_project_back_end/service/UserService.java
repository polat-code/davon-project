package com.example.davon_project_back_end.service;

import com.example.davon_project_back_end.dto.request.AddUserRequest;
import com.example.davon_project_back_end.dto.request.UpdateUserRequest;
import com.example.davon_project_back_end.dto.response.UserResponse;
import com.example.davon_project_back_end.model.User;
import com.example.davon_project_back_end.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public ResponseEntity<List<UserResponse>> getAllUsers() {

        List<User> users = userRepository.findAll();
        List<UserResponse> userResponseList = new ArrayList<>();

        users.forEach((user -> {userResponseList.add(
                                        new UserResponse(
                                                user.getId(),
                                                user.getName(),
                                                user.getSurname(),
                                                user.getTelephone(),
                                                user.getStudentNumber()));}));
        return new ResponseEntity<>(userResponseList, HttpStatus.OK);

    }

    public ResponseEntity<String> addNewUser(AddUserRequest addUserRequest) {
        if(checkIfStudentNumberAlready(addUserRequest.getStudentNumber())){
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


    public boolean checkIfStudentNumberAlready(String studentNumber ){
        Optional<User> optionalUser = userRepository.findByStudentNumber(studentNumber);
        return optionalUser.isPresent();
    }

    public ResponseEntity<String> updateUser(UpdateUserRequest updateUserRequest) {
        Optional<User> userByStudentNumber = userRepository.findByStudentNumber(updateUserRequest.getStudentNumber());
        if(checkIfStudentNumberAlready(updateUserRequest.getStudentNumber()) && !updateUserRequest.getId().equals(userByStudentNumber.get().getId())) {
            return new ResponseEntity<>("This studentNumber is used by another user",HttpStatus.NOT_ACCEPTABLE);
        }

        User user = User.builder()
                .id(updateUserRequest.getId())
                .name(updateUserRequest.getName())
                .surname(updateUserRequest.getSurname())
                .telephone(updateUserRequest.getTelephone())
                .studentNumber(updateUserRequest.getStudentNumber())
                .build();
        userRepository.save(user);
        return new ResponseEntity<>("User is updated successfully",HttpStatus.OK);


    }

    public ResponseEntity<String> deleteUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            return new ResponseEntity<>("There is no such an id to delete",HttpStatus.NOT_FOUND);
        }
        userRepository.delete(user.get());
        return new ResponseEntity<>("User is deleted successfully",HttpStatus.OK);
    }
}
