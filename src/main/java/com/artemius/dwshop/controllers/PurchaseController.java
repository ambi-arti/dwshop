package com.artemius.dwshop.controllers;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.artemius.dwshop.entities.CartItem;
import com.artemius.dwshop.entities.Order;
import com.artemius.dwshop.entities.OrderStatus;
import com.artemius.dwshop.entities.Role;
import com.artemius.dwshop.repositories.CartItemRepository;
import com.artemius.dwshop.repositories.OrderRepository;
import com.artemius.dwshop.services.MerchService;
import com.artemius.dwshop.services.imps.MerService;

@Controller
public class PurchaseController {
    @Autowired
    OrderRepository os; //to be replaced with service;
    @Autowired
    CartItemRepository cs; //to be replaced with service;
    @Autowired
    MerchService ms = new MerService();
    
    
    @GetMapping("/purchase")
    @Secured("CONSUMER")
    public String purchase(Map<String,Object> model, Principal principal) {
	List<CartItem> items = cs.findAllByUsername(principal.getName());
	List<Order> orders = new ArrayList<>();
	boolean purchaseSuccess = true;
	String purchaseDescription = "Ваш заказ был оплачен. В ближайшее время он поступит на доставку.";
	String purchaseStatus = "Оплата заказа прошла успешно!";
	DateFormat f = new SimpleDateFormat("DD.MM.YYYY");
	for (CartItem i: items) {
	    if (i.getQuantity()>i.getMerchSizeFK().getQuantity()) {
		purchaseSuccess=false;
		purchaseStatus = "Оплата заказа не удалась!";
		purchaseDescription = "Мы искренне сожалеем об этом. Вы пытаетесь заказать больше товара, чем есть на складе: "
		+i.getMerchFK().getTitle()+" присутствует на складе в нужном размере в "+i.getMerchSizeFK().getQuantity()
		+", в то время как вы пытаетесь заказать "+i.getQuantity()+" экземпляров.";
		break;
	    }
	    Order o = new Order();
	    o.setItemFK(i);
	    o.setDate(f.format(new Date()));
	    o.setStatus(OrderStatus.Ожидается);	 
	    i.setDiscarded(true);
	}
	model.put("purchaseStatus",purchaseStatus);
	model.put("purchaseDescription",purchaseDescription);
	if (purchaseSuccess)
	    os.saveAll(orders);
	return "/purchase";
    }
}
