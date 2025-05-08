package com.example.crud.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.crud.operation.Repository.AccountRepository;
import com.example.crud.operation.entity.Account;
import com.example.crud.operation.service.AccountServiceImpl;

@SpringBootTest
public class AccountServiceImplTest {

	  @InjectMocks
	    private AccountServiceImpl accountService;

	    @Mock
	    private AccountRepository accountRepository;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this); // Initializes @Mock and @InjectMocks
	    }

	    @Test
	    void testCreateAccount() {
	        Account mockAccount = new Account("John Doe", 1000.0);
	        when(accountRepository.save(any(Account.class))).thenReturn(mockAccount);

	        Account createdAccount = accountService.createAccount(mockAccount);

	        assertNotNull(createdAccount);
	        assertEquals("John Doe", createdAccount.getAccount_holder_name());
	        assertEquals(1000.0, createdAccount.getAccount_balance());
	        verify(accountRepository, times(1)).save(mockAccount);
	    }

	    @Test
	    void testGetAccountDetailsByAccountNumber_found() {
	        Long accountNumber = 1L;
	        Account mockAccount = new Account("Jane Doe", 2000.0);
	        when(accountRepository.findById(accountNumber)).thenReturn(Optional.of(mockAccount));

	        Account account = accountService.getAccountDetailsByAccountNumber(accountNumber);

	        assertNotNull(account);
	        assertEquals("Jane Doe", account.getAccount_holder_name());
	        assertEquals(2000.0, account.getAccount_balance());
	        verify(accountRepository, times(1)).findById(accountNumber);
	    }

	    @Test
	    void testGetAccountDetailsByAccountNumber_notFound() {
	        Long accountNumber = 1L;
	        when(accountRepository.findById(accountNumber)).thenReturn(Optional.empty());

	        Account account = accountService.getAccountDetailsByAccountNumber(accountNumber);

	        assertEquals(null, account);
	        verify(accountRepository, times(1)).findById(accountNumber);
	    }

	    @Test
	    void testGetAllAccountsDetails() {
	        Account account1 = new Account("Alice", 5000.0);
	        Account account2 = new Account("Bob", 3000.0);
	        when(accountRepository.findAll()).thenReturn(Arrays.asList(account1, account2));

	        List<Account> accounts = accountService.getAllAccountsDetails();

	        assertEquals(2, accounts.size());
	        assertEquals("Alice", accounts.get(0).getAccount_holder_name());
	        assertEquals("Bob", accounts.get(1).getAccount_holder_name());
	        verify(accountRepository, times(1)).findAll();
	    }

	    @Test
	    void testDepositAmount_success() {
	        Long accountNumber = 1L;
	        Double depositAmount = 1500.0;
	        Account mockAccount = new Account("Charlie", 3000.0);
	        when(accountRepository.findById(accountNumber)).thenReturn(Optional.of(mockAccount));
	        when(accountRepository.save(any(Account.class))).thenReturn(mockAccount);

	        Account updatedAccount = accountService.depositAmount(accountNumber, depositAmount);

	        assertNotNull(updatedAccount);
	        assertEquals(4500.0, updatedAccount.getAccount_balance());
	        verify(accountRepository, times(1)).findById(accountNumber);
	        verify(accountRepository, times(1)).save(mockAccount);
	    }

	    @Test
	    void testDepositAmount_accountNotFound() {
	        Long accountNumber = 1L;
	        Double depositAmount = 1500.0;
	        when(accountRepository.findById(accountNumber)).thenReturn(Optional.empty());

	        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
	            accountService.depositAmount(accountNumber, depositAmount);
	        });

	        assertEquals("Account is not present", exception.getMessage());
	        verify(accountRepository, times(1)).findById(accountNumber);
	        verify(accountRepository, never()).save(any(Account.class)); // Ensure save is not called
	    }
	}

