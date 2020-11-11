package com.artemius.dwshop.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.artemius.dwshop.entities.Colour;
import com.artemius.dwshop.entities.Discount;
import com.artemius.dwshop.entities.Material;
import com.artemius.dwshop.entities.Merch;
import com.artemius.dwshop.entities.MerchProperty;
import com.artemius.dwshop.entities.MerchSize;
import com.artemius.dwshop.entities.Property;
import com.artemius.dwshop.services.AccountService;
import com.artemius.dwshop.services.ColourService;
import com.artemius.dwshop.services.DiscountService;
import com.artemius.dwshop.services.MaterialService;
import com.artemius.dwshop.services.MerchService;
import com.artemius.dwshop.services.PropertyService;
import com.artemius.dwshop.services.SizeService;
import com.artemius.dwshop.services.imps.AccService;
import com.artemius.dwshop.services.imps.ColService;
import com.artemius.dwshop.services.imps.DiscService;
import com.artemius.dwshop.services.imps.MSizeService;
import com.artemius.dwshop.services.imps.MatService;
import com.artemius.dwshop.services.imps.MerService;
import com.artemius.dwshop.services.imps.PropService;

@Controller
public class MerchController {
    
    @Autowired
    private MerchService m = new MerService();  
    

    @RequestMapping("/casual")
    public String casual(Map<String,Object> model, Principal principal) {
	m.casual(model,principal);
        return "casual";
    }

    @RequestMapping("/index")
    public String index(Map<String,Object> model) {
        return "redirect:/casual";
    }
      
    
    @PostMapping("/merchInfo")
    public String merchInfo(@RequestParam(name = "merchID",required=true)Long merchID, Map<String,Object> model) {
	m.merchInfo(merchID,model);
	return "merchInfo";
    }
    
    @PostMapping("/carousel")
    public String carousel(@RequestParam(name = "sort",required=false)String sort, @RequestParam(name = "section",required=true)String section, Map<String,Object> model) {
	//m.merchInfo(merchID,model);
	m.carousel(model,sort,section);
	return "carousel";
    }
    

}
