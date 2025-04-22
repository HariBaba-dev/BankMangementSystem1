package com.example.crud.operation.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.crud.operation.entity.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public User save(User user) {
		if (user.getId() == null) {
			// Insert new user
			String insertSql = "INSERT INTO user (user_number, name, balance) VALUES (?, ?, ?)";
			jdbcTemplate.update(insertSql, user.getUserNumber(), user.getName(), user.getBalance());
			// Optionally, you can retrieve the generated ID and set it back to the user
			// object
			// For that, you might need to modify the User class to include an ID field
		} else {
			// Update existing user
			String updateSql = "UPDATE user SET user_number = ?, name = ?, balance = ? WHERE id = ?";
			jdbcTemplate.update(updateSql, user.getUserNumber(), user.getName(), user.getBalance(), user.getId());
		}
		return user; // Return the user object (with updated ID if it was newly created)
	}

	@Override
	public User findByUserNumber(Long userNumber) {
		String sql = "SELECT * FROM user WHERE user_number = ?";
		List<User> users = jdbcTemplate.query(sql, new Object[] { userNumber },
				new BeanPropertyRowMapper<>(User.class));
		return users.isEmpty() ? null : users.get(0);
	}

	@Override
	public Optional<User> findById(Long id) {
		String sql = "SELECT * FROM user WHERE id = ?";
		List<User> users = jdbcTemplate.query(sql, new Object[] { id }, new BeanPropertyRowMapper<>(User.class));
		return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
	}

	@Override
	public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM user",
                new BeanPropertyRowMapper<>(User .class));
    }

	@Override
	public void deleteById(Long id) {
		   jdbcTemplate.update("DELETE FROM user WHERE id = ?", id);
		
	}
	}

