package com.artemius.dwshop.controllers;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.artemius.dwshop.services.PurchaseService;
import com.artemius.dwshop.services.imps.PurService;

@Controller
public class PurchaseController {
    
    @Autowired
    PurchaseService ps = new PurService();
    
    @GetMapping("/purchase")
    @Secured("CONSUMER")
    public String purchase(Map<String,Object> model, Principal principal) {
	ps.purchse(model,principal);
	return "/purchase";
    }
}
