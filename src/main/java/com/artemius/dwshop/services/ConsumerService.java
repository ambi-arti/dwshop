package com.artemius.dwshop.services;

import java.security.Principal;
import java.text.ParseException;
import java.util.Map;

import com.artemius.dwshop.entities.EditedAccount;
import com.artemius.dwshop.entities.Order;

public interface ConsumerService {
    public boolean account(Map<String,Object> model, EditedAccount user, Principal principal) throws ParseException;
    public void account(Map<String,Object> model, Principal principal);
    public void fillCart(Map<String,Object> model, Principal principal);
    public void incrementCart(Long itemId, Map<String,Object> model, Principal principal);
    public void decrementCart(Long itemId, Map<String,Object> model, Principal principal);
    public void removeCart(Long itemId, Map<String,Object> model, Principal principal);
    public String addCart(Long merchId, Long amount, Long sizeId, Map<String,Object> model, Principal principal);
    public void orders(Map<String,Object> model, Principal principal);
    public void ordersRemove(Long itemId, Map<String,Object> model, Principal principal);
    public void ordersRate(Long itemId, Long mark, Map<String,Object> model, Principal principal);
    public void removeConfirm(Map<String, Object> model, Principal principal);
    public Iterable<Order> ordersByAccount(Principal principa);
}
