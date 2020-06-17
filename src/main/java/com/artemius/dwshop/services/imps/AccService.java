package com.artemius.dwshop.services.imps;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artemius.dwshop.entities.Account;
import com.artemius.dwshop.repositories.AccountRepository;
import com.artemius.dwshop.services.AccountService;

@Service
public class AccService implements AccountService {

    @Autowired
    AccountRepository ass;
    
    public boolean isUsernameUnique(String username) {
	return (ass.findByUsername(username)==null);
    }
    
    public boolean isUsernameMatchingPattern(String username) {
	String regex = "^(.+)@(.+)$";
	Pattern pattern = Pattern.compile(regex);
	Matcher matcher = pattern.matcher(username);
	return matcher.matches();
    }
    
    public boolean isPasswordLongEnough(String password) {
	return (password.length()>=8);
    }


    @Override
    public Account saveNewAccount(Account account) {
	return ass.saveAndFlush(account);
    }

    @Override
    public Account findByUsername(String username) {
	return ass.findByUsername(username);
    }

    @Override
    public List<Account> findAllByCity(String city) {
	return findAllByCity(city);
    }

}
