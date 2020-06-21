package com.artemius.dwshop.controllers;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.artemius.dwshop.services.DeliveryService;
import com.artemius.dwshop.services.imps.DelService;

@Controller
@Secured("DELIVERY")
public class DeliveryController {
    
    @Autowired
    DeliveryService ds = new DelService();
    
    @GetMapping("/delivery")
    public String delivery(Map<String,Object> model, Principal principal) {
	if (principal==null)
	    return "redirect:/index";
	ds.delivery(model,principal);
	return "delivery";
    }

    
    @PostMapping("delivery_accept")
    @Secured("DELIVERY")
    public String accept(@RequestParam(name="itemId",required=true)Long itemId,
	    Map<String,Object> model,
	    Principal principal) {
	if (principal==null)
	    return "redirect:/index";
	ds.accept(itemId,model,principal);
	return "delcontents";
    }
    
    @PostMapping("delivery_reject")
    public String reject(@RequestParam(name="itemId",required=true)Long itemId,
	    @RequestParam(name="comment",required=true) String comment,
	    Map<String,Object> model,
	    Principal principal) {
	if (principal==null)
	    return "redirect:/index";
	ds.reject(itemId, comment, model,principal);
	return "delcontents";
    
    }
}
