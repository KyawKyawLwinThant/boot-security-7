package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepoistory extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);

    @Query("select u from User u where u.email=:email")
    Optional<User> searchEmailByUser(@Param("email") String email);
}
