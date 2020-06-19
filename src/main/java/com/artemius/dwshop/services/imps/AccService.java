package com.artemius.dwshop.services.imps;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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
	try {
	Account u = ass.findByUsername(username);
	if (u!=null)
	    return false;
	}
	catch (NullPointerException npe) {
	    //do sweet nothing, gotta ignore it
	}
	return true;
    }
    
    public boolean isUsernameMatchingPattern(String username) {
	String regex = "^(.+)@(.+)$";
	Pattern pattern = Pattern.compile(regex);
	Matcher matcher = pattern.matcher(username);
	return matcher.matches();
    }
    
    public boolean isCityMatchingPattern(String city) {
	//String regex = "/^[A-ZА-ЯЁ\\s-]+$/i";
	String regex = "|^[-А-Я-а-я]+$|i";
	Pattern pattern = Pattern.compile(regex);
	Matcher matcher = pattern.matcher(city);
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

    @Override
    public boolean isDateOkay(String date) {
	boolean isDateOkay = true;
	DateFormat f = new SimpleDateFormat("DD-MM-YYYY");
	Date d = new Date();
	try {
	    d = f.parse(date);
	}
	catch(ParseException pe) {
	    isDateOkay = false;
	}
	Date c = new Date();
	if (d.compareTo(c)==0)
	    isDateOkay=false;
	return isDateOkay;
    }

}
