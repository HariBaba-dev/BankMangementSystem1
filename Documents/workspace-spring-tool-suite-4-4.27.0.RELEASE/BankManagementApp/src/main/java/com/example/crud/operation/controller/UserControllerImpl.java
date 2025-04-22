package com.example.crud.operation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.operation.entity.User;
import com.example.crud.operation.service.UserService;



@RestController
@RequestMapping("/user")
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService service;

    @PostMapping("/create")
    @Override
    public User createUser(@RequestBody User user) {
        return service.createUser(user);
    }

    @GetMapping("/{userNumber}")
    @Override
    public ResponseEntity<User> getUserByUserNumber(@PathVariable Long userNumber) {
        User user = service.getUserDetailsByUserNumber(userNumber);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @GetMapping("/getall")
    @Override
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @PutMapping("/deposit/{id}")
    @Override
    public String depositToUser(@PathVariable Long id, @RequestParam Double amount) {
        User user = service.depositAmount(id, amount);
        return user != null ? "Amount deposited: " + user.getBalance() : "User not found";
    }}