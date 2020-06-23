package com.artemius.dwshop.services;

import java.security.Principal;
import java.util.Map;

import com.artemius.dwshop.entities.Account;

public interface AdminiumService {
    
    public void accounts(Map<String,Object> model, Principal principal);
    public void editAccount(Account acc, Map<String,Object> model, Principal principal);
    public void removeAccount(Long itemId, Map<String, Object> model, Principal principal);

}
