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
import com.artemius.dwshop.entities.MSize;
import com.artemius.dwshop.entities.Material;
import com.artemius.dwshop.entities.Merch;
import com.artemius.dwshop.entities.MerchProperty;
import com.artemius.dwshop.entities.MerchSize;
import com.artemius.dwshop.entities.Property;
import com.artemius.dwshop.repositories.MerchRepository;
import com.artemius.dwshop.services.ColourService;
import com.artemius.dwshop.services.DiscountService;
import com.artemius.dwshop.services.MaterialService;
import com.artemius.dwshop.services.MerchService;
import com.artemius.dwshop.services.PropertyService;
import com.artemius.dwshop.services.SizeService;
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
    @Autowired
    private MaterialService ms = new MatService();
    @Autowired
    private PropertyService ps = new PropService();
    @Autowired
    private DiscountService ds = new DiscService();
    @Autowired
    private ColourService cs = new ColService();
    @Autowired
    private SizeService ss = new MSizeService();
    

    @RequestMapping("/index")
    public String index(Map<String,Object> model) {
	Iterable<Merch> merchList =  m.findBySection("Casual");
	model.put("merchList",merchList);
        return "index";
    }
    
    @RequestMapping("/merchInfo")
    public String merchInfo(@RequestParam(name = "merchID",required=true)Long merchID, Map<String,Object> model) {
	Optional<Merch> merch = m.findById(merchID);
	Iterable<Material> matList = ms.getMaterialsByMerchID(merchID);
	List<MerchProperty> valuesList = ps.getMerchPropertysByMerchID(merchID); //список значений
	List<Property> propList = ps.getPropertysByMerchID(merchID); //список свойств
	List<Colour> colList = cs.getColoursByMerchID(merchID);
	List<Discount> discList = ds.getDiscountsByMerchID(merchID);
	List<MerchSize> sizeList = ss.getMerchSizesByMerchID(merchID);
	String typeTitle = m.findTypeNameByMerchID(merchID);
	Double finalPrice = (double)(merch.get().getPrice());
	for (Discount ds: discList) {
	    finalPrice*=ds.getValue();
	}
	model.put("merch",merch);
	model.put("matList",matList);
	model.put("propList",propList);
	model.put("valuesList",valuesList);
	model.put("colList",colList);
	model.put("discList",discList);
	model.put("sizeList",sizeList);
	model.put("finalPrice",finalPrice);
	model.put("typeTitle",typeTitle);
	return "merchInfo";
    }
    
    public String carousel (Map<String,Object> model) {
	//model.put("merchList",merchList);
	return "carousel";
    }

}
