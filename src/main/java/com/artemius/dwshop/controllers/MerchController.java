package com.artemius.dwshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.artemius.dwshop.entities.Merch;
import com.artemius.dwshop.repositories.MerchRepository;

@Controller
public class MerchController {
    
    @Autowired
    private MerchRepository m;

    @RequestMapping("/index")
    public String greeting(Model model) {
        //model.addAttribute("name", name);
        return "greeting";
    }
    
    public String carousel (Model model) {
	Iterable<Merch> merchList = m.findAll();
	model.addAttribute("merchList",merchList);
	return "carousel";
    }

}
