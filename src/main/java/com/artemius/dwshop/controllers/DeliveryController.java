package com.artemius.dwshop.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.artemius.dwshop.entities.Account;
import com.artemius.dwshop.entities.Order;
import com.artemius.dwshop.entities.OrderStatus;
import com.artemius.dwshop.repositories.OrderRepository;
import com.artemius.dwshop.services.AccountService;
import com.artemius.dwshop.services.imps.AccService;

@Controller
@Secured("DELIVERY")
public class DeliveryController {
    
    @Autowired
    OrderRepository os; //to be replaced with service
    @Autowired
    AccountService ass = new AccService(); //to be replaced with service
    
    @GetMapping("/delivery")
    public String delivery(Map<String,Object> model, Principal principal) {
	if (principal==null)
	    return "redirect:/index";
	Account user = ass.findByUsername(principal.getName());
        List<Order> items = os.findAllByCity("Саратов");
        if (items.size()>0)
            model.put("hasItems","hasItems");       
	model.put("user",user);
	model.put("items",items);
	return "delivery";
    }

    
    @PostMapping("delivery_accept")
    @Secured("DELIVERY")
    public String accept(@RequestParam(name="itemId",required=true)Long itemId,
	    Map<String,Object> model,
	    Principal principal) {
	if (principal==null)
	    return "redirect:/index";
	Optional<Order> toAccept = os.findById(itemId);
	toAccept.get().setStatus(OrderStatus.Доставлен);
	os.save(toAccept.get());
	Account user = ass.findByUsername(principal.getName());
        List<Order> items = os.findAllByCity("Саратов");
        if (items.size()>0)
            model.put("hasItems","hasItems");       
	model.put("user",user);
	model.put("items",items);
	return "delcontents";
    }
    
    @PostMapping("delivery_reject")
    public String reject(@RequestParam(name="itemId",required=true)Long itemId,
	    Map<String,Object> model,
	    Principal principal) {
	if (principal==null)
	    return "redirect:/index";
	Optional<Order> toReject = os.findById(itemId);
	toReject.get().setStatus(OrderStatus.Отказ);
	os.save(toReject.get());
	Account user = ass.findByUsername(principal.getName());
        List<Order> items = os.findAllByCity("Саратов");
        if (items.size()>0)
            model.put("hasItems","hasItems");       
	model.put("user",user);
	model.put("items",items);
	return "delcontents";
    
    }
}
