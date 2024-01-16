package com.magazin.main.services;

import com.magazin.main.entities.User;
import com.magazin.main.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    public User getUser(UUID id) {
        return repository.findById(id).get();
    }

    public List<User> getAllUsers(UUID adminId){
        String adminCheck = repository.findUserById(adminId).getRole();
        List<User> errorThrow = new ArrayList<>();

        if (adminCheck.equals("admin")){
            return repository.findAll();
        }
        else return errorThrow;
    }
}
