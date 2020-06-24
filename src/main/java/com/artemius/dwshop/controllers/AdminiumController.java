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
import com.artemius.dwshop.entities.MerchColour;
import com.artemius.dwshop.entities.MerchDiscount;
import com.artemius.dwshop.entities.MerchProperty;
import com.artemius.dwshop.entities.MerchSize;
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
    
    @PostMapping("/adminium/merchcolour")
    @Secured("ADMIN")
    public String merchColour(Map<String,Object> model, Principal principal) {
	as.merchcolour(model,principal);
	return "adminmerchcolour";
    }
    
    @PostMapping("/adminium/merchproperty")
    @Secured("ADMIN")
    public String merchProperty(Map<String,Object> model, Principal principal) {
	as.merchprops(model,principal);
	return "adminmerchproperty";
    }
    
    @PostMapping("/adminium/merchdisc")
    @Secured("ADMIN")
    public String merchDisc(Map<String,Object> model, Principal principal) {
	as.merchdisc(model,principal);
	return "adminmerchdiscount";
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
    public String newAMerch(Merch m,
	    Map<String,Object> model,
	    Principal principal) {
	as.newMerch(m,model,principal);
	return "adminmerch";
    }
    
    @PostMapping("/adminium/remove_merchsize")
    @Secured("ADMIN")
    public String removeMerchsize(@RequestParam(name="itemId",required=true)Long itemId,
	    Map<String,Object> model,
	    Principal principal) {
	as.removeMerchSize(itemId,model,principal);
	return "adminmerchsizes";
    }
    
    @PostMapping("/adminium/edit_merchsize")
    @Secured("ADMIN")
    public String editMerchsize(MerchSize m,
	    Map<String,Object> model,
	    Principal principal) {
	as.editMerchSize(m,model,principal);
	return "adminmerchsizes";
    }
    
    @PostMapping("/adminium/new_merchsize")
    @Secured("ADMIN")
    public String newMerchsize(MerchSize m,
	    Map<String,Object> model,
	    Principal principal) {
	as.newMerchSize(m,model,principal);
	return "adminmerchsizes";
    }
    
    @PostMapping("/adminium/remove_merchcolour")
    @Secured("ADMIN")
    public String removeMerchcolour(@RequestParam(name="itemId",required=true)Long itemId,
	    Map<String,Object> model,
	    Principal principal) {
	as.removeMerchColour(itemId,model,principal);
	return "adminmerchcolour";
    }
    
    @PostMapping("/adminium/edit_merchcolour")
    @Secured("ADMIN")
    public String editMerchcolour(MerchColour m,
	    Map<String,Object> model,
	    Principal principal) {
	as.editMerchСolour(m,model,principal);
	return "adminmerchcolour";
    }
    
    @PostMapping("/adminium/new_merchcolour")
    @Secured("ADMIN")
    public String newMerchcolour(MerchColour m,
	    Map<String,Object> model,
	    Principal principal) {
	as.newMerchColour(m,model,principal);
	return "adminmerchcolour";
    }
    
    @PostMapping("/adminium/remove_merchprops")
    @Secured("ADMIN")
    public String removeMerchproperty(@RequestParam(name="itemId",required=true)Long itemId,
	    Map<String,Object> model,
	    Principal principal) {
	as.removeMerchProps(itemId,model,principal);
	return "adminmerchprops";
    }
    
    @PostMapping("/adminium/edit_merchprops")
    @Secured("ADMIN")
    public String editMerch(MerchProperty m,
	    Map<String,Object> model,
	    Principal principal) {
	as.editMerchProps(m,model,principal);
	return "adminmerchprops";
    }
    
    @PostMapping("/adminium/new_merchprops")
    @Secured("ADMIN")
    public String newAMerch(MerchProperty m,
	    Map<String,Object> model,
	    Principal principal) {
	as.newMerchProps(m,model,principal);
	return "adminmerchprops";
    }
    
    @PostMapping("/adminium/remove_merchdisc")
    @Secured("ADMIN")
    public String removeMerchdisc(@RequestParam(name="itemId",required=true)Long itemId,
	    Map<String,Object> model,
	    Principal principal) {
	as.removeMerchDisc(itemId,model,principal);
	return "adminmerchdiscount";
    }
    
    @PostMapping("/adminium/edit_merchdisc")
    @Secured("ADMIN")
    public String editMerch(MerchDiscount m,
	    Map<String,Object> model,
	    Principal principal) {
	as.editMerchDisc(m,model,principal);
	return "adminmerchdiscount";
    }
    
    @PostMapping("/adminium/new_merchdisc")
    @Secured("ADMIN")
    public String newAMerch(MerchDiscount m,
	    Map<String,Object> model,
	    Principal principal) {
	as.newMerchDisc(m,model,principal);
	return "adminmerchdiscount";
    }
}
