package com.example.crud.operation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.operation.entity.Account;
import com.example.crud.operation.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountControllerImpl implements AccountController {

    @Autowired
    private AccountService service;

    @Override
    @PostMapping("/create")
    public Account createAccount(@RequestBody Account account) {
        return service.createAccount(account);
    }

    @Override
    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccountByAccountNumber(@PathVariable Long accountNumber) {
        Account account = service.getAccountDetailsByAccountNumber(accountNumber);
        if (account == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(account);
    }

    @Override
    @GetMapping("/getallaccounts")
    public List<Account> getAllAccountDetails() {
        return service.getAllAccountsDetails();
    }

    @Override
    @PutMapping("/path/{id}")
    public String depositAccount(@PathVariable Long id, @RequestParam Double amount) {
        Account account = service.depositAmount(id, amount);
        return account.toString();
    }

//	@Override
//	public Account createAccount(Account account) {
//		// TODO Auto-generated method stub
//		return null;
	}


	

