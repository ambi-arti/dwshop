package com.artemius.dwshop.services;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.mail.javamail.JavaMailSender;

import com.artemius.dwshop.entities.Account;

public interface AccountService {
    public boolean registration(Map<String,Object> model, Account user) throws ParseException;
    public boolean isUsernameUnique(String username);
    public boolean isUsernameMatchingPattern(String username);
    public boolean isCityMatchingPattern(String city);
    public boolean isPasswordLongEnough(String password);
    public boolean isPasswordUnique(String password, Account account);
    public boolean isDateOkay(String date) throws ParseException;
    public Account saveNewAccount(Account account);
    public Account findByUsername(String username);
    public List<Account> findAllByCity(String city);
    public int sendCongratsEmail(String sendTo, String fullName);
    public void cityList(Map<String,Object> model, Long regionId) throws ParseException;
    public void getRegionsList(Map<String,Object> model, Long countryId);
}
