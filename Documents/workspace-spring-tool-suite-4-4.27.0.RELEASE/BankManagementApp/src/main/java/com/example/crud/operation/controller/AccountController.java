package com.example.crud.operation.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.example.crud.operation.entity.Account;


public interface AccountController {
    Account createAccount(Account account);
    ResponseEntity<Account> getAccountByAccountNumber(Long accountNumber);
    List<Account> getAllAccountDetails();
    String depositAccount(Long id, Double amount);
}
