package com.example.davon_project_back_end.repository;

import com.example.davon_project_back_end.model.User;
import org.assertj.core.api.Assertions;
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
        userRepository.save(user);
        User savedUser = userRepository.findByStudentNumber(user.getStudentNumber()).get();

        Assertions.assertThat(savedUser).isNotNull();


    }



}
