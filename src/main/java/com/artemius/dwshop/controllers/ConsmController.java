package com.artemius.dwshop.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.artemius.dwshop.entities.CartItem;
import com.artemius.dwshop.entities.Account;
import com.artemius.dwshop.entities.MerchDiscount;
import com.artemius.dwshop.entities.Roles;
import com.artemius.dwshop.repositories.CartItemRepository;
import com.artemius.dwshop.services.AccountService;
import com.artemius.dwshop.services.DiscountService;
import com.artemius.dwshop.services.MerchService;
import com.artemius.dwshop.services.SizeService;
import com.artemius.dwshop.services.imps.AccService;
import com.artemius.dwshop.services.imps.DiscService;
import com.artemius.dwshop.services.imps.MSizeService;
import com.artemius.dwshop.services.imps.MerService;

@Controller
public class ConsmController {
    
    @Autowired
    MerchService ms = new MerService();
    @Autowired
    AccountService ass = new AccService();
    @Autowired
    DiscountService ds = new DiscService();
    @Autowired
    SizeService ss = new MSizeService();
    @Autowired
    CartItemRepository cs;   //to be replaced with service
    
   @GetMapping("/cart")
    public String cart(Map<String,Object> model) { //redirects to the cart page
	return "cart";
    }
    
    @PostMapping("/cart_add") //adding some merch
    @ResponseBody
    @Transactional
    public String addToCart(/*@RequestParam(name="action",required=true)String action,*/
    	    @RequestParam(name="id",required=true) Long merchId,
	    @RequestParam(name="amount",required=true) int amount,
	    @RequestParam(name="size",required=true) Long sizeId,
	    Map<String,Object> model,
	    Principal principal) {
    	if (principal!=null) {
    	    if (amount>ss.getMerchSizeByID(sizeId).get().getQuantity())
    		return "Слишком мало товара на складе!";
    	    CartItem ci = new CartItem();
    	    ci.setMerchFK(ms.findById(merchId).get());
    	    ci.setMerchSizeFK(ss.getMerchSizeByID(sizeId).get());
    	    ci.setQuantity(amount);
    	    double cost = ms.findById(merchId).get().getPrice();
    	    List<MerchDiscount> discs = ds.getMerchDiscountsByMerchID(merchId);
    	    List<Double> values = new ArrayList<>();
    	    for (MerchDiscount md:discs) 
    		values.add(md.getDiscountFK().getValue());
    	    for (Double val: values)
    		cost*=val;
    	    cost*=amount;
    	    ci.setCost(cost);
    	    ci.setConsumerFK(ass.findByUsername(principal.getName()));
    	    cs.save(ci);
    	    return "Готово!";
    	    
    	}
    	return "Войдите в аккаунт!";
    }
    
  /* @PostMapping("/cart") //removing cart items by item id
   public String removeFromCart(@RequestParam(name="action",required=true)String action,
	   @RequestParam(name="cartItem",required=true) Long itemId) {
       return "";
   }*/

}
