package com.example.davon_project_back_end.service;

import com.example.davon_project_back_end.dto.request.AddUserRequest;
import com.example.davon_project_back_end.dto.request.UpdateUserRequest;
import com.example.davon_project_back_end.dto.response.UserResponse;
import com.example.davon_project_back_end.model.User;
import com.example.davon_project_back_end.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
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

import java.util.ArrayList;
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
        // TODO CHECK ALL USERS
        //Page<User> users = Mockito.mock(Page.class);

        User user1  =User.builder().id(1L).name("Özgürhan").surname("Polat").studentNumber("260201035").telephone("05531521381").build();
        User user2 = User.builder().id(2l).name("Emre").surname("Yurdagül").studentNumber("260201049").telephone("05343423423").build();
        User user3 = User.builder().id(3l).name("Yasir").surname("Duman").studentNumber("2702030102").telephone("0443324234234").build();

        List<User> listUser = new ArrayList<>();
        listUser.add(user1);
        listUser.add(user2);
        listUser.add(user3);

        when(userRepository.findAll()).thenReturn(listUser);
        ResponseEntity<List<UserResponse>>  responseUsers = userService.getAllUsers();

        UserResponse firstUser = responseUsers.getBody().get(0);
        UserResponse secondUser = responseUsers.getBody().get(1);
        UserResponse thirdUser = responseUsers.getBody().get(2);

        // Control the size of user list
        Assertions.assertThat(responseUsers.getBody().size()).isEqualTo(3);

        // Control the first User
        Assertions.assertThat(user1.getId()).isEqualTo(firstUser.userId());
        Assertions.assertThat(user1.getName()).isEqualTo(firstUser.name());
        Assertions.assertThat(user1.getSurname()).isEqualTo(firstUser.surname());
        Assertions.assertThat(user1.getStudentNumber()).isEqualTo(firstUser.studentNumber());
        Assertions.assertThat(user1.getTelephone()).isEqualTo(firstUser.telephone());

        // Control the second user
        Assertions.assertThat(user2.getId()).isEqualTo(secondUser.userId());
        Assertions.assertThat(user2.getName()).isEqualTo(secondUser.name());
        Assertions.assertThat(user2.getSurname()).isEqualTo(secondUser.surname());
        Assertions.assertThat(user2.getStudentNumber()).isEqualTo(secondUser.studentNumber());
        Assertions.assertThat(user2.getTelephone()).isEqualTo(secondUser.telephone());

        // Control the third user
        Assertions.assertThat(user3.getId()).isEqualTo(thirdUser.userId());
        Assertions.assertThat(user3.getName()).isEqualTo(thirdUser.name());
        Assertions.assertThat(user3.getSurname()).isEqualTo(thirdUser.surname());
        Assertions.assertThat(user3.getTelephone()).isEqualTo(thirdUser.telephone());
        Assertions.assertThat(user3.getStudentNumber()).isEqualTo(thirdUser.studentNumber());


    }

    @Test
    public void UserService_addNewUser_ReturnPositiveResponseEntity() {
        // TODO CODE COVERAGE %80
        // Arrange
        AddUserRequest addUserRequest = AddUserRequest.builder()
                .name("Özgürhan")
                .surname("Polat")
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

        // Act

        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        // Positive Test
        ResponseEntity<String> positiveResponse = new ResponseEntity<>("User is added successfully!", HttpStatus.OK);
        when(userRepository.findByStudentNumber("26020103523")).thenReturn(Optional.empty());
        Assertions.assertThat(userService.addNewUser(addUserRequest)).isEqualTo(positiveResponse);



    }
    @Test
    public void UserService_addNewUser_ReturnNegativeResponseEntity() {
        // TODO CODE COVERAGE %80
        // Arrange
        AddUserRequest addUserRequest = AddUserRequest.builder()
                .name("Özgürhan")
                .surname("Polat")
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

        // Act

        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        when(userRepository.findByStudentNumber("260201035")).thenReturn(Optional.of(user));

        // Negative Test
        ResponseEntity<String> negativeResponse = new ResponseEntity<>("Already used student number",HttpStatus.NOT_ACCEPTABLE);
        Assertions.assertThat(userService.addNewUser(addUserRequest)).isEqualTo(negativeResponse);




    }

    @Test
    public void UserService_checkIfStudentNumberAlready_ReturnBoolean() {
        String studentNumber = "260201035";
        when(userRepository.findByStudentNumber(studentNumber)).thenReturn(Optional.empty());
        Assertions.assertThat(userService.checkIfStudentNumberAlready(studentNumber)).isEqualTo(false);
    }

    @Test
    public void UserService_updateUser_ReturnNoUserResponseEntity() {
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
        ResponseEntity<String> responseEntity = new ResponseEntity<>("There is no user!",HttpStatus.NOT_ACCEPTABLE);


        //when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.findById(1L)).thenReturn(Optional.empty());


        //Assertions.assertThat(userService.updateUser(updateValidUserRequest)).isEqualTo(responseEntity);
        Assertions.assertThat(userService.updateUser(updateValidUserRequest)).isEqualTo(responseEntity);
    }

    @Test
    public void UserService_updateUser_ReturnAlreadyUserResponseEntity() {
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


        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);


        //Assertions.assertThat(userService.updateUser(updateValidUserRequest)).isEqualTo(responseEntity);
        Assertions.assertThat(userService.updateUser(updateValidUserRequest)).isEqualTo(responseEntity);
    }

    @Test
    public void UserService_deleteUser_ReturnNoUserResponseEntity() {
        // Arrange
        Long userId = 1L;
        User user = User.builder()
                .name("Özgürhan")
                        .surname("Polat")
                                .telephone("05531521381")
                                        .studentNumber("260201035")
                                                .build();



        // Act

        when(userRepository.findById(1L)).thenReturn(Optional.empty());


        // Assertion
        Assertions.assertThat(userService.deleteUser(userId)).isEqualTo(new ResponseEntity<>("There is no such an id to delete",HttpStatus.NOT_FOUND));


    }
    @Test
    public void UserService_deleteUser_ReturnAlreadyUserResponseEntity() {
        // Arrange
        Long userId = 1L;
        User user = User.builder()
                .name("Özgürhan")
                .surname("Polat")
                .telephone("05531521381")
                .studentNumber("260201035")
                .build();



        // Act
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));



        // Assertion
        Assertions.assertThat(userService.deleteUser(userId)).isEqualTo(new ResponseEntity<>("User is deleted successfully",HttpStatus.OK));



    }






}
