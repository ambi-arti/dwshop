package com.artemius.dwshop.controllers;

import java.text.ParseException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.artemius.dwshop.entities.Account;
import com.artemius.dwshop.services.AccountService;
import com.artemius.dwshop.services.imps.AccService;

@Controller
public class AuthController {
    
    @Autowired
    AccountService as = new AccService();
    
    @RequestMapping("/registration")
    public String registration(Map<String,Object> model) {
	as.getRegionsList(model,(long)1);
        return "registration";
    }
    
    @PostMapping("/registration")
    public String registration(Account user,Map<String,Object> model) throws ParseException {
	boolean isSuccessful = as.registration(model,user);
	if (!isSuccessful)
	    return "registration";
	return "redirect:/index";
    }
    
    @PostMapping("/getCities")
    public String getCities(Account user,Map<String,Object> model, @RequestParam(name = "regionId",required=true)Long regionId) throws ParseException {
	as.cityList(model,regionId);
	return "citylist";
    }
}
