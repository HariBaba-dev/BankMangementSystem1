package com.example.crud.operation.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.crud.operation.entity.User;

public interface UserController {
	 User createUser(User user);
	    ResponseEntity<User> getUserByUserNumber(Long userNumber);
	    List<User> getAllUsers();
	    String depositToUser(Long id, Double amount);
}
