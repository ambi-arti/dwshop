package com.artemius.dwshop.controllers;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.artemius.dwshop.entities.Account;
import com.artemius.dwshop.services.AdminiumService;
import com.artemius.dwshop.services.imps.AdmService;

@Controller
public class AdminiumController {
    
    @Autowired
    AdminiumService as = new AdmService();
    
    @GetMapping("/adminium")
    @Secured("ADMIN")
    public String adminium(Map<String,Object> model, Principal principal) {
	as.accounts(model,principal);
	return "adminium";
    }
    
    @PostMapping("/adminium/remove_account")
    public String removeAccount(@RequestParam(name="itemId",required=true)Long itemId,
	    Map<String,Object> model,
	    Principal principal) {
	as.removeAccount(itemId,model,principal);
	return "admincontents";
    }
    
    @PostMapping("adminium/edit_account")
    public String removeAccount(Account acc,
	    Map<String,Object> model,
	    Principal principal) {
	as.editAccount(acc,model,principal);
	return "admincontents";
    }
    
    @PostMapping("adminium/new_account")
    public String newAccount(Account acc,
	    Map<String,Object> model,
	    Principal principal) {
	as.newAccount(acc,model,principal);
	return "admincontents";
    }
    
}
