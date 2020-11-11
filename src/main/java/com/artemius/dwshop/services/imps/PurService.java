package com.artemius.dwshop.services.imps;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artemius.dwshop.entities.CartItem;
import com.artemius.dwshop.entities.MerchSize;
import com.artemius.dwshop.entities.Order;
import com.artemius.dwshop.entities.OrderStatus;
import com.artemius.dwshop.repositories.CartItemRepository;
import com.artemius.dwshop.repositories.OrderRepository;
import com.artemius.dwshop.repositories.SectionRepository;
import com.artemius.dwshop.services.AccountService;
import com.artemius.dwshop.services.PurchaseService;
import com.artemius.dwshop.services.SizeService;

@Service
public class PurService implements PurchaseService {
    @Autowired
    private OrderRepository os; //to be replaced with service;
    @Autowired
    private CartItemRepository cs; //to be replaced with service;
    @Autowired
    private SizeService ss = new MSizeService();
    @Autowired
    private AccountService ass = new AccService();
    @Autowired
    private SectionRepository ses;

    @Override
    public void purchse(Map<String, Object> model, Principal principal) {
	List<CartItem> items = cs.findAllByUsername(principal.getName());
	List<Order> orders = new ArrayList<>();
	boolean purchaseSuccess = true;
	String purchaseDescription = "Ваш заказ был оплачен. В ближайшее время он поступит на доставку.";
	String purchaseStatus = "Оплата заказа прошла успешно!";
	DateFormat f = new SimpleDateFormat("d MMMM YYYY");
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
	    //i.setDiscarded(true);
	    orders.add(o);
	    cs.removeById(i.getId_PK());
	}
	model.put("user",ass.findByUsername(principal.getName()));
	model.put("purchaseStatus",purchaseStatus);
	model.put("purchaseDescription",purchaseDescription);
	model.put("sections",ses.findAll());   
	if (purchaseSuccess) {
	    os.saveAll(orders);
	    for (CartItem i: items) {
		MerchSize toSave = i.getMerchSizeFK();
		long q = ss.getMerchSizeByID(toSave.getId_PK()).get().getQuantity();
		toSave.setQuantity(q-i.getQuantity());
		ss.save(toSave);
	    }
	}
    }

}
