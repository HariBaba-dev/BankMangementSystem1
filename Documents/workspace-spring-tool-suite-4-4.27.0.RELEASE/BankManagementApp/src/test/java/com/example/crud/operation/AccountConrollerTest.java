package com.example.crud.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.example.crud.operation.controller.AccountControllerImpl;
import com.example.crud.operation.entity.Account;
import com.example.crud.operation.service.AccountService;

@SpringBootTest
public class AccountConrollerTest {

	@Mock
	private AccountService service;

	@InjectMocks
	private AccountControllerImpl controller;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this); 
	}

	@Test
	void testCreateAccount() {
		Account mockAccount = new Account("John Doe", 1000.0);
		when(service.createAccount(any(Account.class))).thenReturn(mockAccount);

		Account result = controller.createAccount(mockAccount);

		assertNotNull(result);
		assertEquals("John Doe", result.getAccount_holder_name());
		assertEquals(1000.0, result.getAccount_balance());
		verify(service, times(1)).createAccount(mockAccount);
	}

	@Test
	void testGetAccountByAccountNumber_found() {
		Long accNum = 1L;
		Account mockAccount = new Account("Jane Doe", 2000.0);
		when(service.getAccountDetailsByAccountNumber(accNum)).thenReturn(mockAccount);

		ResponseEntity<Account> response = controller.getAccountByAccountNumber(accNum);

		assertEquals(200, response.getStatusCodeValue());
		assertEquals(2000.0, response.getBody().getAccount_balance());
	}

	@Test
	void testGetAccountByAccountNumber_notFound() {
		when(service.getAccountDetailsByAccountNumber(1L)).thenReturn(null);

		ResponseEntity<Account> response = controller.getAccountByAccountNumber(1L);

		assertEquals(404, response.getStatusCodeValue());
		assertNull(response.getBody());
	}

	@Test
	void testGetAllAccountDetails() {
		List<Account> mockList = Arrays.asList(new Account("Alice", 5000.0), new Account("Bob", 3000.0));
		when(service.getAllAccountsDetails()).thenReturn(mockList);

		List<Account> result = controller.getAllAccountDetails();

		assertEquals(2, result.size());
		verify(service, times(1)).getAllAccountsDetails();
	}

	@Test
	void testDepositAccount() {
		Long id = 10L;
		Double amount = 1500.0;
		Account mockAccount = new Account("Charlie", 3000.0);
		when(service.depositAmount(id, amount)).thenReturn(mockAccount);

		String response = controller.depositAccount(id, amount);

		assertTrue(response.contains("Charlie"));
		verify(service, times(1)).depositAmount(id, amount);
	}

}
