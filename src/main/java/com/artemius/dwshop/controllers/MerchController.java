package com.artemius.dwshop.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.artemius.dwshop.entities.Merch;
import com.artemius.dwshop.entities.MerchSize;
import com.artemius.dwshop.repositories.MerchRepository;
import com.artemius.dwshop.repositories.MerchSizeRepository;

@Controller
public class MerchController {
    
    @Autowired
    private MerchRepository m;
    private MerchSizeRepository mS;
    private Iterable<Merch> merchList = m.findAll();

    @RequestMapping("/index")
    public String index(Map<String,Object> model) {
        //model.addAttribute("name", name);
	//Iterable<Merch> merchList = m.findAll();
	model.put("merchList",merchList);
        return "index";
    }
    
    @RequestMapping("/test")
    public String test(Model model) {
        //model.addAttribute("name", name);
	//Iterable<Merch> merchList = m.findAll();
	model.addAttribute("merchList",merchList);
        return "test";
    }
    
    @RequestMapping("/merchInfo")
    public String merchInfo(@RequestParam(name = "merchID",required=true)Long merchID, Model model) {
	Optional<Merch> merch = m.findById(merchID);
	model.addAttribute("merch",merch);
	return "merchInfo";
    }
    
    public String carousel (Map<String,Object> model) {
	//model.put("merchList",merchList);
	return "carousel";
    }

}
