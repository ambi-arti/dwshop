package com.artemius.dwshop.services.imps;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artemius.dwshop.entities.Account;
import com.artemius.dwshop.repositories.AccountRepository;
import com.artemius.dwshop.services.AccountService;
import com.artemius.dwshop.services.AdminiumService;

@Service
public class AdmService implements AdminiumService {
    
    @Autowired
    AccountRepository acc;

    @Override
    public void accounts(Map<String, Object> model, Principal principal) {
	fillTheModel(model,principal);
    }
    
    public void removeAccount(Long itemId, Map<String, Object> model, Principal principal) {
	try {
	if (acc.findById(itemId).isPresent())
	    acc.removeByIdPK(itemId);
	}
	catch(Exception e) {
	    model.put("error",e.getLocalizedMessage());
	}
	fillTheModel(model,principal);
	
    }

    @Override
    public void editAccount(Account a, Map<String, Object> model, Principal principal) {
	try {
	    Account toEdit = acc.findByUsername(a.getUsername());
	    toEdit.setActive(a.getActive());
	    toEdit.setSurname(a.getSurname());
	    toEdit.setFirstname(a.getFirstname());
	    toEdit.setPatronymic(a.getPatronymic());
	    toEdit.setBirthdate(a.getBirthdate());
	    toEdit.setCity(a.getCity());
	    toEdit.setAddress(a.getAddress());
	    toEdit.setUsername(a.getUsername());
	    toEdit.setPassword(a.getPassword());
	    toEdit.setRoles(a.getRoles());
	    acc.saveAndFlush(toEdit);
	}
	catch(Exception e) {
	    model.put("error",e.getLocalizedMessage());
	}
	fillTheModel(model,principal);

    }
    
    private void fillTheModel(Map<String, Object> model, Principal principal) {
	model.put("items",acc.findAll());
	model.put("user",acc.findByUsername(principal.getName()));
	model.put("currentItem","account");
    }

}
