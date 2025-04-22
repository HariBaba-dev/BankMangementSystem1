package com.example.crud.operation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud.operation.entity.Account;

public interface AccountRepository  extends JpaRepository<Account, Long>{

}
