package com.example.davon_project_back_end.service;

import com.example.davon_project_back_end.dto.request.AddUserRequest;
import com.example.davon_project_back_end.dto.request.UpdateUserRequest;
import com.example.davon_project_back_end.dto.response.UserResponse;
import com.example.davon_project_back_end.model.User;
import com.example.davon_project_back_end.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

//@ExtendWith(MockitoExtension.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;


    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @BeforeEach
    public void init() {
        //MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void UserService_getAllUsers_ReturnAllUsers() {
        Page<User> users = Mockito.mock(Page.class);


        when(userRepository.findAll(Mockito.any(Pageable.class))).thenReturn(users);

        ResponseEntity<List<UserResponse>>  responseUsers = userService.getAllUsers();
        Assertions.assertThat(responseUsers).isNotNull();

    }

    @Test
    public void UserService_addNewUser_ReturnResponseEntity() {

        AddUserRequest addUserRequest = AddUserRequest.builder()
                .name("Özgürhan")
                .surname("Polat")
                .studentNumber("260201035")
                .telephone("05531521381")
                .build();
        User user = User.builder()
                .name("Özgürhan")
                .surname("Polat")
                .studentNumber("260201035")
                .telephone("05531521381")
                .build();
        ResponseEntity<String> response = new ResponseEntity<>("User is added successfully!", HttpStatus.OK);

        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Assertions.assertThat(userService.addNewUser(addUserRequest)).isEqualTo(response);

    }

    @Test
    public void UserService_checkIfStudentNumberAlready_ReturnBoolean() {
        String studentNumber = "260201035";
        when(userRepository.findByStudentNumber(studentNumber)).thenReturn(Optional.empty());
        Assertions.assertThat(userService.checkIfStudentNumberAlready(studentNumber)).isEqualTo(false);
    }

    @Test
    public void UserService_updateUser_ReturnResponseEntity() {
        UpdateUserRequest updateValidUserRequest = UpdateUserRequest.builder()
                .id(1L)
                .name("Özgürhan")
                .surname("Kutlu")
                .studentNumber("260201035")
                .telephone("05531521381")
                .build();

        User user = User.builder()
                .id(1L)
                        .name("Özgürhan")
                                .surname("Polat")
                                        .studentNumber("260201035")
                                                .telephone("05531521381")
                                                        .build();
        ResponseEntity<String> responseEntity = new ResponseEntity<>("User is updated successfully",HttpStatus.OK);


        //when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);


        //Assertions.assertThat(userService.updateUser(updateValidUserRequest)).isEqualTo(responseEntity);
        Assertions.assertThat(userService.updateUser(updateValidUserRequest)).isEqualTo(new ResponseEntity<>("There is no user!",HttpStatus.NOT_ACCEPTABLE));
    }

    @Test
    public void UserService_deleteUser_ReturnResponseEntity() {
        // Arrange
        Long userId = 1L;
        User user = User.builder()
                .name("Özgürhan")
                        .surname("Polat")
                                .telephone("05531521381")
                                        .studentNumber("260201035")
                                                .build();



        // Act
        //when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.findById(1L)).thenReturn(Optional.empty());


        // Assertion
        //Assertions.assertThat(userService.deleteUser(userId)).isEqualTo(new ResponseEntity<>("User is deleted successfully",HttpStatus.OK));
        Assertions.assertThat(userService.deleteUser(userId)).isEqualTo(new ResponseEntity<>("There is no such an id to delete",HttpStatus.NOT_FOUND));


    }






}
