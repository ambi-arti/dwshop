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
import com.artemius.dwshop.entities.Merch;
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
    
    @PostMapping("/adminium/accounts")
    @Secured("ADMIN")
    public String accounts(Map<String,Object> model, Principal principal) {
	as.accounts(model,principal);
	return "adminaccounts";
    }
    
    @PostMapping("/adminium/merch")
    @Secured("ADMIN")
    public String merch(Map<String,Object> model, Principal principal) {
	as.merch(model,principal);
	return "adminmerch";
    }
    
    @PostMapping("/adminium/merchsize")
    @Secured("ADMIN")
    public String merchSize(Map<String,Object> model, Principal principal) {
	as.merchsize(model,principal);
	return "adminmerchsizes";
    }
    
    @PostMapping("/adminium/remove_account")
    @Secured("ADMIN")
    public String removeAccount(@RequestParam(name="itemId",required=true)Long itemId,
	    Map<String,Object> model,
	    Principal principal) {
	as.removeAccount(itemId,model,principal);
	return "adminaccounts";
    }
    
    @PostMapping("/adminium/edit_account")
    @Secured("ADMIN")
    public String editAccount(Account acc,
	    Map<String,Object> model,
	    Principal principal) {
	as.editAccount(acc,model,principal);
	return "adminaccounts";
    }
    
    @PostMapping("/adminium/new_account")
    @Secured("ADMIN")
    public String newAccount(Account acc,
	    Map<String,Object> model,
	    Principal principal) {
	as.newAccount(acc,model,principal);
	return "adminaccounts";
    }
    
    @PostMapping("/adminium/remove_merch")
    @Secured("ADMIN")
    public String removeMerch(@RequestParam(name="itemId",required=true)Long itemId,
	    Map<String,Object> model,
	    Principal principal) {
	as.removeMerch(itemId,model,principal);
	return "adminmerch";
    }
    
    @PostMapping("/adminium/edit_merch")
    @Secured("ADMIN")
    public String editMerch(Merch m,
	    Map<String,Object> model,
	    Principal principal) {
	as.editMerch(m,model,principal);
	return "adminmerch";
    }
    
    @PostMapping("/adminium/new_merch")
    @Secured("ADMIN")
    public String newAccount(Merch m,
	    Map<String,Object> model,
	    Principal principal) {
	as.newMerch(m,model,principal);
	return "adminmerch";
    }
    
}
