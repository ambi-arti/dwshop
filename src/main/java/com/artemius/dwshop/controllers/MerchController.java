package com.artemius.dwshop.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.artemius.dwshop.entities.Colour;
import com.artemius.dwshop.entities.Discount;
import com.artemius.dwshop.entities.Material;
import com.artemius.dwshop.entities.Merch;
import com.artemius.dwshop.entities.MerchProperty;
import com.artemius.dwshop.entities.Property;
import com.artemius.dwshop.repositories.MerchRepository;
import com.artemius.dwshop.services.ColourService;
import com.artemius.dwshop.services.DiscountService;
import com.artemius.dwshop.services.MaterialService;
import com.artemius.dwshop.services.PropertyService;
import com.artemius.dwshop.services.imps.ColService;
import com.artemius.dwshop.services.imps.DiscService;
import com.artemius.dwshop.services.imps.MatService;
import com.artemius.dwshop.services.imps.PropService;

@Controller
public class MerchController {
    
    //The following code is an obvious shit: TESTING PURPOSES ONLY!
    //It is a precursor to the Services foundation
    
    @Autowired
    private MerchRepository m;  
    @Autowired
    private MaterialService ms = new MatService();
    @Autowired
    private PropertyService ps = new PropService();
    @Autowired
    private DiscountService ds = new DiscService();
    @Autowired
    private ColourService cs = new ColService();
    

    @RequestMapping("/index")
    public String index(Map<String,Object> model) {
	Long firstID = (long)1;
	Iterable<Merch> merchList =  m.findAll();
	Optional<Merch> merch = m.findById(firstID);
	List<Material> matList = ms.getMaterialsByMerchID(firstID);
	List<MerchProperty> valuesList = ps.getMerchPropertysByMerchID(firstID); //список значений
	List<Property> propList = ps.getPropertysByMerchID(firstID); //список свойств
	List<Colour> colList = cs.getColoursByMerchID(firstID);
	List<Discount> discList = ds.getDiscountsByMerchID(firstID);
	Double finalPrice = (double)(merch.get().getPrice());
	for (Discount dis: discList) {
	    finalPrice*=dis.getValue();
	}
	model.put("merch",merch);
	model.put("matList",matList);
	model.put("merchList",merchList);
	model.put("propList",propList);
	model.put("valuesList",valuesList);
	model.put("colList",colList);
	model.put("discList",discList);
	model.put("finalPrice",finalPrice);
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
	List<MerchProperty> valuesList = ps.getMerchPropertysByMerchID(merchID); //список значений
	List<Property> propList = ps.getPropertysByMerchID(merchID); //список свойств
	List<Colour> colList = cs.getColoursByMerchID(merchID);
	model.put("merch",merch);
	model.put("matList",matList);
	model.put("propList",propList);
	model.put("valuesList",valuesList);
	model.put("colList",colList);
	return "merchInfo";
    }
    
    public String carousel (Map<String,Object> model) {
	//model.put("merchList",merchList);
	return "carousel";
    }

}
