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
	boolean isSuccessful = ass.registration(model,user);
	if (!isSuccessful)
	    return "registration";
	return "redirect:/index";
    }
}
