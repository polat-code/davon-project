package com.example.davon_project_back_end.repository;

import com.example.davon_project_back_end.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByStudentNumber(String studentNumber);


    Optional<User> findByIdAndStudentNumber(Long aLong,String studentNumber);
}
