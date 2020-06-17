package com.artemius.dwshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.artemius.dwshop.entities.Account;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    public Account findByUsername(String username);
    public List<Account> findAllByCity(String city);
}