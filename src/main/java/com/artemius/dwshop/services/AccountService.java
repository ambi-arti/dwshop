package com.artemius.dwshop.services;

import java.util.List;

import com.artemius.dwshop.entities.Account;

public interface AccountService {
    public boolean isUsernameUnique(String username);
    public boolean isUsernameMatchingPattern(String username);
    public boolean isPasswordLongEnough(String password);
    public Account saveNewAccount(Account account);
    public Account findByUsername(String username);
    public List<Account> findAllByCity(String city);
}
