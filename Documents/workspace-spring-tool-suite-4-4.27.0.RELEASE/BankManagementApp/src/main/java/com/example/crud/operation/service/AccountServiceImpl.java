package com.example.crud.operation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.operation.Repository.AccountRepository;
import com.example.crud.operation.entity.Account;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository repo;

	@Override
	public Account createAccount(Account account) {
		Account account_saved = repo.save(account);
		return account_saved;
	}

	@Override
	public Account getAccountDetailsByAccountNumber(Long accountNumber) {
		Optional<Account> account = repo.findById(accountNumber);
		return account.orElse(null); // Return null if not found
	}

	@Override
	public List<Account> getAllAccountsDetails() {
		List<Account> listOfAccounts = repo.findAll();
		return listOfAccounts;
	}

	@Override
	public Account depositAmount(Long accountNumber, Double amount) {
		Optional<Account> account = repo.findById(accountNumber);
		if(account.isEmpty()) {
			throw new RuntimeException("Account is not present");
		}
		Account accountPresent = account.get();
		Double totalBalance = accountPresent.getAccount_balance()+amount; 
		accountPresent.setAccount_balance(totalBalance);
		repo.save(accountPresent);
		return accountPresent;
		}
  
	@Override
	public Account withdrawAmount(Long accountNumber, Double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void closeAccount(Long accountNumber) {
		// TODO Auto-generated method stub

	}

}
