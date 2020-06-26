package com.artemius.dwshop.controllers;

import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.artemius.dwshop.entities.CartItem;
import com.artemius.dwshop.entities.EditedAccount;
import com.artemius.dwshop.entities.Account;
import com.artemius.dwshop.entities.MerchDiscount;
import com.artemius.dwshop.entities.Order;
import com.artemius.dwshop.entities.Role;
import com.artemius.dwshop.entities.Roles;
import com.artemius.dwshop.repositories.CartItemRepository;
import com.artemius.dwshop.repositories.MerchSizeRepository;
import com.artemius.dwshop.repositories.OrderRepository;
import com.artemius.dwshop.services.AccountService;
import com.artemius.dwshop.services.ConsumerService;
import com.artemius.dwshop.services.DiscountService;
import com.artemius.dwshop.services.MerchService;
import com.artemius.dwshop.services.SizeService;
import com.artemius.dwshop.services.imps.AccService;
import com.artemius.dwshop.services.imps.ConService;
import com.artemius.dwshop.services.imps.DiscService;
import com.artemius.dwshop.services.imps.MSizeService;
import com.artemius.dwshop.services.imps.MerService;

@Controller
@Secured("CONSUMER")
public class ConsmController {
    
    @Autowired
    ConsumerService css = new ConService();
    
   @GetMapping("/account")
   public String account(Map<String,Object> model, Principal principal) {
      /* if (principal==null)
      	    return "redirect:/login";*/
       css.account(model,principal);
       return "account";
   }
   
   @GetMapping("/disablepage")
   public String disablePage(Map<String,Object> model, Principal principal) {
      /* if (principal==null)
      	    return "redirect:/login";*/
       return "disablepage";
   }
   
   @GetMapping("/removeConfirm")
   public String removeConfirm(Map<String,Object> model, Principal principal) {
      /* if (principal==null)
      	    return "redirect:/login";*/
       css.removeConfirm(model,principal);
       return "redirect:/logout";
   }
   
   @PostMapping("/account")
   public String account(Map<String,Object> model, EditedAccount user, Principal principal) throws ParseException {
       EditedAccount u = user;
       if (css.account(model,u,principal))
	   return "redirect:/index";
       return "/account";
   }
   @GetMapping("/cart")
    public String cart(Map<String,Object> model, Principal principal) { //redirects to the cart page
       /*	if (principal==null)
       	    return "redirect:/login";*/
       	css.fillCart(model,principal);
	return "cart";
    }
   
   @PostMapping("/cart_increment")
   public String incrementCart(@RequestParam(name="itemId",required=true)Long itemId,
	    Map<String,Object> model,
	    Principal principal) {
	if (principal==null)
      	    return "redirect:/login";
	css.incrementCart(itemId,model,principal);
	return "cartcontents";
   }
   
   @PostMapping("/cart_decrement") 
   public String decrementCart(@RequestParam(name="itemId",required=true)Long itemId,
	    Map<String,Object> model,
	    Principal principal) throws Exception{
	/*if (principal==null)
      	    return "redirect:/login";*/
	css.decrementCart(itemId,model,principal);
	return "cartcontents";
   }
   
    @PostMapping("/cart_remove")
    public String removeFromCart(@RequestParam(name="itemId",required=true)Long itemId,
	    Map<String,Object> model,
	    Principal principal) {
	/*if (principal==null)
       	    return "redirect:/login";*/
	css.removeCart(itemId,model,principal);
	return "cartcontents";
    }
    
    @PostMapping("/cart_add") //adding some merch
    @ResponseBody
    @Transactional
    public String addToCart(/*@RequestParam(name="action",required=true)String action,*/
    	    @RequestParam(name="id",required=true) Long merchId,
	    @RequestParam(name="amount",required=true) Long amount,
	    @RequestParam(name="size",required=true) Long sizeId,
	    Map<String,Object> model,
	    Principal principal) {
	return css.addCart(merchId,amount,sizeId,model,principal);
    }
    
    @GetMapping("/orders")
    public String orders(Map<String,Object> model, Principal principal) { //redirects to the cart page
       /*	if (principal==null)
       	    return "redirect:/login";	*/
       	css.orders(model,principal);
	return "orders";
    }
    
    @PostMapping("/order_remove")
    public String ordersRemove(@RequestParam(name = "orderId", required = true)Long orderId, 
	    Map<String,Object> model, 
	    Principal principal) {
	css.ordersRemove(orderId,model,principal);
	return "ordercontents";
    }
    
    @PostMapping("/order_rate")
    public String ordersRate(@RequestParam(name = "orderId", required = true)Long orderId, 
	    @RequestParam(name = "mark", required = true)Long mark, 
	    Map<String,Object> model, 
	    Principal principal) {
	css.ordersRate(orderId,mark,model,principal);
	return "ordercontents";
    }

}
