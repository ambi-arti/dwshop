package com.artemius.dwshop.services;

import java.security.Principal;
import java.util.Map;

import com.artemius.dwshop.entities.Account;
import com.artemius.dwshop.entities.Merch;
import com.artemius.dwshop.entities.MerchColour;
import com.artemius.dwshop.entities.MerchDiscount;
import com.artemius.dwshop.entities.MerchProperty;
import com.artemius.dwshop.entities.MerchSize;

public interface AdminiumService {
    
    public void accounts(Map<String,Object> model, Principal principal);
    public void merch(Map<String,Object> model, Principal principal);
    
    public void merchsize(Map<String,Object> model, Principal principal);
    
    public void merchcolour(Map<String,Object> model, Principal principal);
    public void merchdisc(Map<String,Object> model, Principal principal);
    public void merchprops(Map<String,Object> model, Principal principal);
    
    public void editAccount(Account acc, Map<String,Object> model, Principal principal);
    public void newAccount(Account acc, Map<String,Object> model, Principal principal);
    public void removeAccount(Long itemId, Map<String, Object> model, Principal principal);
    
    public void editMerch(Merch m, Map<String,Object> model, Principal principal);
    public void newMerch(Merch m, Map<String,Object> model, Principal principal);
    public void removeMerch(Long itemId, Map<String, Object> model, Principal principal);
    
    public void editMerchSize(MerchSize m, Map<String,Object> model, Principal principal);
    public void newMerchSize(MerchSize m, Map<String,Object> model, Principal principal);
    public void removeMerchSize(Long itemId, Map<String, Object> model, Principal principal);
    
    public void editMerchСolour(MerchColour m, Map<String,Object> model, Principal principal);
    public void newMerchColour(MerchColour m, Map<String,Object> model, Principal principal);
    public void removeMerchColour(Long itemId, Map<String, Object> model, Principal principal);
    
    public void editMerchDisc(MerchDiscount m, Map<String,Object> model, Principal principal);
    public void newMerchDisc(MerchDiscount m, Map<String,Object> model, Principal principal);
    public void removeMerchDisc(Long itemId, Map<String, Object> model, Principal principal);
    
    public void editMerchProps(MerchProperty m, Map<String,Object> model, Principal principal);
    public void newMerchProps(MerchProperty m, Map<String,Object> model, Principal principal);
    public void removeMerchProps(Long itemId, Map<String, Object> model, Principal principal);
}
