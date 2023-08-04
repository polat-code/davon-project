package com.example.davon_project_back_end.repository;

import com.example.davon_project_back_end.model.User;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void UserRepository_findByStudentNumber_ReturnUser(){
        User user = User.builder()
                .name("özgürhan")
                .surname("polat")
                .studentNumber("260201035")
                .telephone("05531521381")
                .build();
        User userSaved  = userRepository.save(user);

        // Check whether returned value is equal to user.
        Assertions.assertThat(userSaved.getId()).isEqualTo(user.getId());
        Assertions.assertThat(userSaved.getName()).isEqualTo(user.getName());
        Assertions.assertThat(userSaved.getSurname()).isEqualTo(user.getSurname());
        Assertions.assertThat(userSaved.getTelephone()).isEqualTo(user.getTelephone());
        Assertions.assertThat(userSaved.getStudentNumber()).isEqualTo(user.getStudentNumber());

        // Check whether query returns correct user.
        User returnedUser = userRepository.findByStudentNumber(user.getStudentNumber()).get();

        Assertions.assertThat(user.getId()).isEqualTo(returnedUser.getId());
        Assertions.assertThat(user.getName()).isEqualTo(returnedUser.getName());
        Assertions.assertThat(user.getSurname()).isEqualTo(returnedUser.getSurname());
        Assertions.assertThat(user.getTelephone()).isEqualTo(returnedUser.getTelephone());
        Assertions.assertThat(user.getStudentNumber()).isEqualTo(returnedUser.getStudentNumber());

    }

    @Test
    public void UserRepository_findByIdAndStudentNumber_ReturnUser() {
        User user = User.builder()
                .name("özgürhan")
                .surname("polat")
                .studentNumber("260201035")
                .telephone("05531521381")
                .build();

        // FIXME LOOK AT RETURNED USER AND CHECK WHETHER THEY ARE EQUAL
        User userSaved =  userRepository.save(user);

        // Check whether save method is working or not.
        Assertions.assertThat(userSaved.getId()).isEqualTo(user.getId());
        Assertions.assertThat(userSaved.getName()).isEqualTo(user.getName());
        Assertions.assertThat(userSaved.getSurname()).isEqualTo(user.getSurname());
        Assertions.assertThat(userSaved.getStudentNumber()).isEqualTo(user.getStudentNumber());
        Assertions.assertThat(userSaved.getTelephone()).isEqualTo(user.getTelephone());

        //Check findByIdStudentNumber() is working or not.
        User returnedUser = userRepository.findByIdAndStudentNumber(user.getId(),user.getStudentNumber()).get();
        Assertions.assertThat(returnedUser.getId()).isEqualTo(user.getId());
        Assertions.assertThat(returnedUser.getName()).isEqualTo(user.getName());
        Assertions.assertThat(returnedUser.getSurname()).isEqualTo(user.getSurname());
        Assertions.assertThat(returnedUser.getTelephone()).isEqualTo(user.getTelephone());
        Assertions.assertThat(returnedUser.getStudentNumber()).isEqualTo(user.getStudentNumber());




    }




}
