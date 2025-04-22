package com.example.crud.operation.service;

import java.util.List;

import com.example.crud.operation.entity.User;

public interface UserService {

	 User createUser(User user);
	    User getUserDetailsByUserNumber(Long userNumber);
	    List<User> getAllUsers();
	    User depositAmount(Long id, Double amount);
	    void deleteUser(Long id);
	}
