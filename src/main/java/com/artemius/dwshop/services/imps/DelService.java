package com.artemius.dwshop.services.imps;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artemius.dwshop.entities.Account;
import com.artemius.dwshop.entities.Order;
import com.artemius.dwshop.entities.OrderStatus;
import com.artemius.dwshop.repositories.OrderRepository;
import com.artemius.dwshop.services.AccountService;
import com.artemius.dwshop.services.DeliveryService;

@Service
public class DelService implements DeliveryService {
    
    @Autowired
    OrderRepository os; //to be replaced with service
    @Autowired
    AccountService ass = new AccService(); //to be replaced with service

    @Override
    public void delivery(Map<String, Object> model, Principal principal) {
	Account user = ass.findByUsername(principal.getName());
        List<Order> items = os.findAllByCity(user.getCity());
        if (items.size()>0)
            model.put("hasItems","hasItems");       
	model.put("user",user);
	model.put("items",items);

    }

    @Override
    public void accept(Long itemId, Map<String, Object> model, Principal principal) {
	Optional<Order> toAccept = os.findById(itemId);
	toAccept.get().setStatus(OrderStatus.Доставлен);
	os.save(toAccept.get());
	Account user = ass.findByUsername(principal.getName());
	List<Order> items = os.findAllByCity(user.getCity());
        if (items.size()>0)
            model.put("hasItems","hasItems");       
	model.put("user",user);
	model.put("items",items);
    }

    @Override
    public void reject(Long itemId, String comment, Map<String, Object> model, Principal principal) {
	Optional<Order> toReject = os.findById(itemId);
	toReject.get().setStatus(OrderStatus.Отказ);
	toReject.get().setComment(comment);
	os.save(toReject.get());
	Account user = ass.findByUsername(principal.getName());
	List<Order> items = os.findAllByCity(user.getCity());
        if (items.size()>0)
            model.put("hasItems","hasItems");       
	model.put("user",user);
	model.put("items",items);
    }

}
