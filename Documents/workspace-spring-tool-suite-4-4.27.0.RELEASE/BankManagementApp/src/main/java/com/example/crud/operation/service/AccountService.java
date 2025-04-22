package com.example.crud.operation.service;

import java.util.List;

import com.example.crud.operation.entity.Account;

public interface AccountService {

	public Account createAccount(Account account);
	
	public Account getAccountDetailsByAccountNumber(Long AccountNumber);
	
	public List<Account>getAllAccountsDetails();
	
	public Account depositAmount(Long accountNumber , Double amount);
	
	public Account withdrawAmount(Long accountNumber , Double amount);
	
   public void closeAccount(Long accountNumber);
   
   
	

}
