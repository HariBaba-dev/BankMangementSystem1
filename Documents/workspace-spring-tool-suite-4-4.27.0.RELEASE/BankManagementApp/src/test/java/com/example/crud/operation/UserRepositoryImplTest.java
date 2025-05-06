package com.example.crud.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import com.example.crud.operation.Repository.UserRepositoryImpl;
import com.example.crud.operation.entity.User;

@SpringBootTest
public class UserRepositoryImplTest {
	
	 @InjectMocks
	    private UserRepositoryImpl userRepository;

	    @Mock
	    private JdbcTemplate jdbcTemplate;

	    private User sampleUser;

	    @BeforeEach
	    public void setup() {
	        MockitoAnnotations.openMocks(this);
	        sampleUser = new User();
	        sampleUser.setId(1L);
	        sampleUser.setUserNumber(12345L);
	        sampleUser.setName("John Doe");
	        sampleUser.setBalance(1000.0);
	    }

	    @Test
	    public void testSave_NewUser() {
	        sampleUser.setId(null);
	        when(jdbcTemplate.update(anyString(), any(), any(), any())).thenReturn(1);
	        User savedUser = userRepository.save(sampleUser);
	        assertNotNull(savedUser);
	        verify(jdbcTemplate).update(anyString(), eq(sampleUser.getUserNumber()), eq(sampleUser.getName()), eq(sampleUser.getBalance()));
	    }

	    @Test
	    public void testSave_ExistingUser() {
	        when(jdbcTemplate.update(anyString(), any(), any(), any(), any())).thenReturn(1);
	        User updatedUser = userRepository.save(sampleUser);
	        assertNotNull(updatedUser);
	        verify(jdbcTemplate).update(anyString(), eq(sampleUser.getUserNumber()), eq(sampleUser.getName()), eq(sampleUser.getBalance()), eq(sampleUser.getId()));
	    }

	    @Test
	    public void testFindByUserNumber() {
	        when(jdbcTemplate.query(anyString(), any(Object[].class), any(BeanPropertyRowMapper.class)))
	                .thenReturn(List.of(sampleUser));
	        User foundUser = userRepository.findByUserNumber(12345L);
	        assertNotNull(foundUser);
	        assertEquals("John Doe", foundUser.getName());
	    }

	    @Test
	    public void testFindById() {
	        when(jdbcTemplate.query(anyString(), any(Object[].class), any(BeanPropertyRowMapper.class)))
	                .thenReturn(List.of(sampleUser));
	        Optional<User> userOptional = userRepository.findById(1L);
	        assertTrue(userOptional.isPresent());
	        assertEquals(sampleUser.getId(), userOptional.get().getId());
	    }

	    @Test
	    public void testFindAll() {
	        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class)))
	                .thenReturn(List.of(sampleUser));
	        List<User> users = userRepository.findAll();
	        assertFalse(users.isEmpty());
	    }

	    @Test
	    public void testDeleteById() {
	        userRepository.deleteById(1L);
	        verify(jdbcTemplate).update("DELETE FROM user WHERE id = ?", 1L);
	    }

}
