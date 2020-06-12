package com.artemius.dwshop.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.artemius.dwshop.entities.Material;
import com.artemius.dwshop.entities.Merch;
import com.artemius.dwshop.repositories.MerchRepository;
import com.artemius.dwshop.services.MaterialService;
import com.artemius.dwshop.services.imps.MatService;

@Controller
public class MerchController {
    
    //The following code is an obvious shit: TESTING PURPOSES ONLY!
    //It is a precursor to the Services foundation
    
    @Autowired
    private MerchRepository m;
    @Autowired
    private MaterialService ms = new MatService();

    @RequestMapping("/index")
    public String index(Map<String,Object> model) {
	Iterable<Merch> merchList =  m.findAll();
	Optional<Merch> merch = m.findById((long)1);
	List<Material> matList = ms.getMaterialsByMerchID(merch.get().getId_PK());
	model.put("merch",merch);
	model.put("matList",matList);
	model.put("merchList",merchList);
        return "index";
    }
    
 /*   @RequestMapping("/test")
    public String test(Model model) {
        //model.addAttribute("name", name);
	//Iterable<Merch> merchList = m.findAll();
	Iterable<Merch> merchList = m.findAll();
	model.addAttribute("merchList",merchList);
	model.addAttribute("matList",matList);
        return "test";
    }*/
    
    @RequestMapping("/merchInfo")
    public String merchInfo(@RequestParam(name = "merchID",required=true)Long merchID, Map<String,Object> model) {
	//model.addAttribute("merch",merch);
	Optional<Merch> merch = m.findById(merchID);
	Iterable<Material> matList = ms.getMaterialsByMerchID(merchID);
	model.put("merch",merch);
	model.put("matList",matList);
	return "merchInfo";
    }
    
    public String carousel (Map<String,Object> model) {
	//model.put("merchList",merchList);
	return "carousel";
    }

}
