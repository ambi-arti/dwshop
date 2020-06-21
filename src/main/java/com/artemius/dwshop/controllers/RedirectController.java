package com.artemius.dwshop.controllers;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.artemius.dwshop.entities.Role;
import com.artemius.dwshop.services.AccountService;
import com.artemius.dwshop.services.imps.AccService;

@Controller
public class RedirectController {
    @Autowired
    private AccountService ass = new AccService();
    
    @RequestMapping("/")
    public String root(Map<String,Object> model, Principal principal) {
	if (principal==null)
	    return "redirect:/index";
	Role role = ass.findByUsername(principal.getName()).getRoles();	
	switch(role) {
        	case ADMIN:
        	   return "redirect:/adminium"; 
        	   
		case DELIVERY:
		    return "redirect:/delivery";
		    
		default:
		    return "redirect:/index";
	    
	}
    }	
}
