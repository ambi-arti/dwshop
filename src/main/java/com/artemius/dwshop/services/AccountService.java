package com.artemius.dwshop.services;

import java.util.List;
import java.util.Map;

import com.artemius.dwshop.entities.Account;

public interface AccountService {
    public boolean registration(Map<String,Object> model, Account user);
    public boolean isUsernameUnique(String username);
    public boolean isUsernameMatchingPattern(String username);
    public boolean isCityMatchingPattern(String city);
    public boolean isPasswordLongEnough(String password);
    public boolean isPasswordUnique(String password, Account account);
    public boolean isDateOkay(String date);
    public Account saveNewAccount(Account account);
    public Account findByUsername(String username);
    public List<Account> findAllByCity(String city);
}
