package com.example.crud.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.crud.operation.controller.UserControllerImpl;
import com.example.crud.operation.entity.User;
import com.example.crud.operation.service.UserService;

@SpringBootTest
public class UserControllerImplTest {

	@InjectMocks
	private UserControllerImpl userController;

	@Mock
	private UserService userService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this); // Initializes @Mock and @InjectMocks
	}

	@Test
	void testCreateUser() {
		User mockUser = new User();
		mockUser.setName("John Doe");
		mockUser.setBalance(1000.0);
		when(userService.createUser(any(User.class))).thenReturn(mockUser);
		User createdUser = userController.createUser(mockUser);

		assertNotNull(createdUser);
		assertEquals("John Doe", createdUser.getName());
		assertEquals(1000.0, createdUser.getBalance());
		verify(userService, times(1)).createUser(mockUser);
	}

}
