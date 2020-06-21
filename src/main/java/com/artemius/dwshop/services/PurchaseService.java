package com.artemius.dwshop.services;

import java.security.Principal;
import java.util.Map;

public interface PurchaseService {
    public void purchse(Map<String,Object> model, Principal principal);
}
