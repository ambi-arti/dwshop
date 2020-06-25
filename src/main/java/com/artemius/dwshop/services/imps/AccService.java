package com.artemius.dwshop.services.imps;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import com.artemius.dwshop.entities.Account;
import com.artemius.dwshop.entities.Role;
import com.artemius.dwshop.repositories.AccountRepository;
import com.artemius.dwshop.services.AccountService;

@Service
public class AccService implements AccountService {

    @Autowired
    AccountRepository ass;
    
    public boolean registration(Map<String,Object> model, Account user) {
	boolean flawed = false;
	if (isUsernameUnique(user.getUsername())==false) {
	    model.put("nicknameExists","Данный email уже занят!");
	    flawed=true;
	}
	if (isUsernameMatchingPattern(user.getUsername())==false) {
	    model.put("notAnEmail","Неверный формат E-mail!");
	    flawed=true;
	}
	if (isCityMatchingPattern(user.getCity())==false) {
	    model.put("wrongCity","Неверный формат названия!");
	    flawed=true;
	}
	if (isDateOkay(user.getBirthdate())==false) {
	    model.put("badDate","Неверный формат даты рождения!");
	    flawed=true;
	}
	if (isPasswordLongEnough(user.getPassword())==false) {
	    model.put("passwordTooShort","Пароль содержит менее 8 символов!");
	    flawed=true;
	}
	if (!flawed) {
	    user.setActive(true);
	    user.setPassword(new Pbkdf2PasswordEncoder().encode(user.getPassword()));
	    user.setRoles(Role.DELIVERY);	    
	    ass.save(user);
	    return true;
	}
	model.put("surname",user.getSurname());
	model.put("firstname",user.getFirstname());
	model.put("patronymic",user.getPatronymic());
	model.put("city",user.getCity());
	model.put("birthdate",user.getBirthdate());
	model.put("address",user.getAddress());
	model.put("username",user.getUsername());
	return false;
    }
    
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
    
    public boolean isPasswordUnique(String password, Account account) {
	return !password.equals(account.getPassword());
    }


    @Override
    public Account saveNewAccount(Account account) {
	return ass.save(account);
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
	DateFormat f = new SimpleDateFormat("dd-MM-yyyy");
	Date d = new Date();
	try {
	    d = f.parse(date);
	}
	catch(ParseException pe) {
	    isDateOkay = false;
	}
	Date c = new Date();
	if (c.compareTo(d)>0)
	    isDateOkay=false;
	return isDateOkay;
    }

}
