package com.example.crud.operation.Repository;

import java.util.List;
import java.util.Optional;
import com.example.crud.operation.entity.User;

public interface UserRepository {
	User save(User user);

	User findByUserNumber(Long userNumber);

	Optional<User> findById(Long id);
    List<User> findAll();
    void deleteById(Long id);

}
