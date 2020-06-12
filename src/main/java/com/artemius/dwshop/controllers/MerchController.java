package com.artemius.dwshop.controllers;

import java.util.Map;

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
  //  private Iterable<Merch> merchList = m.findAll();

    @RequestMapping("/index")
    public String index(Map<String,Object> model) {
        //model.addAttribute("name", name);
	Iterable<Merch> merchList = m.findAll();
	model.put("merchList",merchList);
        return "index";
    }
    
    @RequestMapping("/test")
    public String test(Model model) {
        //model.addAttribute("name", name);
	Iterable<Merch> merchList = m.findAll();
	model.addAttribute("merchList",merchList);
        return "test";
    }
    
    public String carousel (Map<String,Object> model) {
	//model.put("merchList",merchList);
	return "carousel";
    }

}
