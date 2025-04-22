package com.example.crud.operation.service;

import com.example.crud.operation.Repository.UserRepository;
import com.example.crud.operation.entity.User;

import com.example.crud.operation.service.UserService; // correct interface

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional; // or you can use org.springframework.transaction.annotation.Transactional


@Service
public class UserServiceImpl implements UserService {

	 @Autowired
	    private UserRepository repository;

	    @Override
	    public User createUser(User user) {
	        return repository.save(user);
	    }

	    @Override
	    public User getUserDetailsByUserNumber(Long userNumber) {
	        return repository.findByUserNumber(userNumber);
	    }

	    @Override
	    public List<User> getAllUsers() {
	        return repository.findAll();
	    }

	    @Override
	    @Transactional
	    public User depositAmount(Long id, Double amount) {
	        return repository.findById(id).map(user -> {
	            Double updatedBalance = user.getBalance() == null ? amount : user.getBalance() + amount;
	            user.setBalance(updatedBalance);
	            return repository.save(user);
	        }).orElse(null);
	    }

	    @Override
	    public void deleteUser(Long id) {
	        repository.deleteById(id);
	    }

}
