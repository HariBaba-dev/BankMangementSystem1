package com.example.crud.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
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

import com.example.crud.operation.Repository.UserRepository;
import com.example.crud.operation.entity.User;
import com.example.crud.operation.service.UserServiceImpl;

@SpringBootTest
public class UserSericeTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userService;

	private User sampleUser;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		sampleUser = new User();
		sampleUser.setId(1L);
		sampleUser.setUserNumber(12345L);
		sampleUser.setName("John Doe");
		sampleUser.setBalance(1000.0);
	}

	@Test
	void testCreateUser() {
		when(userRepository.save(any(User.class))).thenReturn(sampleUser);

		User result = userService.createUser(sampleUser);

		assertNotNull(result);
		assertEquals("John Doe", result.getName());
		verify(userRepository, times(1)).save(sampleUser);
	}

	 @Test
	    void testGetUserDetailsByUserNumber() {
	        when(userRepository.findByUserNumber(12345L)).thenReturn(sampleUser);

	        User result = userService.getUserDetailsByUserNumber(12345L);

	        assertNotNull(result);
	        assertEquals(12345L, result.getUserNumber());
	        verify(userRepository).findByUserNumber(12345L);
	    }

	    @Test
	    void testGetAllUsers() {
	        List<User> userList = Arrays.asList(sampleUser);
	        when(userRepository.findAll()).thenReturn(userList);

	        List<User> result = userService.getAllUsers();

	        assertNotNull(result);
	        assertEquals(1, result.size());
	        verify(userRepository).findAll();
	    }

	    @Test
	    void testDepositAmount_UserExists() {
	        when(userRepository.findById(1L)).thenReturn(Optional.of(sampleUser));
	        when(userRepository.save(any(User.class))).thenReturn(sampleUser);

	        User result = userService.depositAmount(1L, 500.0);

	        assertNotNull(result);
	        assertEquals(1500.0, result.getBalance());
	        verify(userRepository).findById(1L);
	        verify(userRepository).save(sampleUser);
	    }

	    @Test
	    void testDepositAmount_UserNotFound() {
	        when(userRepository.findById(2L)).thenReturn(Optional.empty());

	        User result = userService.depositAmount(2L, 500.0);

	        assertNull(result);
	        verify(userRepository).findById(2L);
	        verify(userRepository, never()).save(any());
	    }

	    @Test
	    void testDeleteUser() {
	        doNothing().when(userRepository).deleteById(1L);

	        userService.deleteUser(1L);

	        verify(userRepository, times(1)).deleteById(1L);
	    }
}
