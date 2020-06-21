package com.artemius.dwshop.services;

import java.security.Principal;
import java.util.Map;

public interface DeliveryService {
    public void delivery(Map<String,Object> model, Principal principal);
    public void accept(Long itemId, Map<String,Object> model, Principal principal);
    public void reject(Long itemId, String comment, Map<String,Object> model, Principal principal);
}
