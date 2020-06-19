package com.artemius.dwshop.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.artemius.dwshop.entities.Account;
import com.artemius.dwshop.entities.Role;
import com.artemius.dwshop.services.AccountService;
import com.artemius.dwshop.services.imps.AccService;

@Controller
public class AuthController {
    
    @Autowired
    AccountService ass = new AccService();
    
    @RequestMapping("/registration")
    public String registration(Map<String,Object> model) {
        return "registration";
    }
    
    @PostMapping("/registration")
    public String registration(Account user,Map<String,Object> model) {
	boolean flawed = false;
	if (ass.isUsernameUnique(user.getUsername())==false) {
	    model.put("nicknameExists","Данный email уже занят!");
	    flawed=true;
	}
	if (ass.isUsernameMatchingPattern(user.getUsername())==false) {
	    model.put("notAnEmail","Неверный формат E-mail!");
	    flawed=true;
	}
	if (ass.isCityMatchingPattern(user.getCity())==false) {
	    model.put("wrongCity","Неверный формат названия!");
	    flawed=true;
	}
	if (ass.isDateOkay(user.getBirthdate())==false) {
	    model.put("badDate","Неверный формат даты рождения!");
	    flawed=true;
	}
	if (ass.isPasswordLongEnough(user.getPassword())==false) {
	    model.put("passwordTooShort","Пароль содержит менее 8 символов!");
	    flawed=true;
	}
	if (!flawed) {
	    user.setActive(true);
	    user.setRoles(Role.CONSUMER);
	    
	    
	    return "redirect:/index";
	}
	model.put("surname",user.getSurname());
	model.put("firstname",user.getFirstname());
	model.put("patronymic",user.getPatronymic());
	model.put("city",user.getCity());
	model.put("birthdate",user.getBirthdate());
	model.put("address",user.getAddress());
	model.put("username",user.getUsername());
        return "registration";
    }
}
