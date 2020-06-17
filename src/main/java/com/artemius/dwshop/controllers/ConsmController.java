package com.artemius.dwshop.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConsmController {
    
    @GetMapping("/cart")
    public String cart(Map<String,Object> model) { //redirects to the cart page
	return "";
    }
    
    @PostMapping("/cart?action=add") //adding some merch
    public String addToCart(@RequestParam(name="id",required=true) Long merchId,
	    @RequestParam(name="amount",required=true) int amount,
	    @RequestParam(name="size",required=true) int sizeId,
	    Map<String,Object> model) {
	return "";
    }
    
   @PostMapping("/cart?action=remove") //removing cart items by item id
   public String removeFromCart(@RequestParam(name="cartItem",required=true) Long itemId) {
       return "";
   }

}
