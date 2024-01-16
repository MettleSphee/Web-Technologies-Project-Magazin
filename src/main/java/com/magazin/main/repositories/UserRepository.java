package com.magazin.main.repositories;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magazin.main.entities.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
    User findUserById(@Param("id")  UUID id);

    List<User> findAll();
}
