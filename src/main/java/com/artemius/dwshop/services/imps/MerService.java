package com.artemius.dwshop.services.imps;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artemius.dwshop.entities.Colour;
import com.artemius.dwshop.entities.Discount;
import com.artemius.dwshop.entities.Material;
import com.artemius.dwshop.entities.Merch;
import com.artemius.dwshop.entities.MerchProperty;
import com.artemius.dwshop.entities.MerchSize;
import com.artemius.dwshop.entities.Property;
import com.artemius.dwshop.entities.Section;
import com.artemius.dwshop.repositories.MerchRepository;
import com.artemius.dwshop.repositories.MerchSizeRepository;
import com.artemius.dwshop.repositories.MerchTypeRepository;
import com.artemius.dwshop.repositories.SectionRepository;
import com.artemius.dwshop.services.AccountService;
import com.artemius.dwshop.services.ColourService;
import com.artemius.dwshop.services.DiscountService;
import com.artemius.dwshop.services.MaterialService;
import com.artemius.dwshop.services.MerchService;
import com.artemius.dwshop.services.PropertyService;
import com.artemius.dwshop.services.SizeService;

@Service
public class MerService implements MerchService {

    @Autowired
    MerchRepository ms;
    @Autowired
    MerchTypeRepository ts;
    @Autowired
    SectionRepository ss;
    @Autowired
    SizeService mss = new MSizeService();
    @Autowired
    AccountService ass = new AccService();
    @Autowired
    MaterialService mat = new MatService();
    @Autowired
    PropertyService ps = new PropService();
    @Autowired
    ColourService cs = new ColService();
    @Autowired
    DiscountService ds = new DiscService();

    @Override
    public List<Merch> findAll() {
	List<Merch> toReturn = new ArrayList<>();
	for (Merch m: ms.findAll() ) {
	    toReturn.add(m);
	}
	return toReturn;
    }
    
    public void casual(Map<String,Object> model, Principal principal) {
	Iterable<Merch> merchList = findBySection(getIdByTitle("casual"));
	model.put("merchList",merchList);
	if (principal!=null)
	    model.put("user",ass.findByUsername(principal.getName()));
    }
    
    public void merchInfo(Long merchID, Map<String,Object> model) {
	Optional<Merch> merch = findById(merchID);
	Iterable<Material> matList = mat.getMaterialsByMerchID(merchID);
	List<MerchProperty> valuesList = ps.getMerchPropertysByMerchID(merchID); //список значений
	List<Property> propList = ps.getPropertysByMerchID(merchID); //список свойств
	List<Colour> colList = cs.getColoursByMerchID(merchID);
	List<Discount> discList = ds.getDiscountsByMerchID(merchID);
	List<MerchSize> sizeList = mss.getMerchSizesByMerchID(merchID);
	String typeTitle = findTypeNameByMerchID(merchID);
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
    }

    @Override
    public Optional<Merch> findById(Long merchID) {
	return ms.findById(merchID);
    }

    @Override
    public List<Merch> findBySection(Long section) {
	List<Merch> toReturn = new ArrayList<>();
	for (Merch m: ms.findAllBySection(section)) {
	    toReturn.add(m);
	}
	return toReturn;
    }

    @Override
    public Merch addNewMerch(Merch merch) {
	return ms.save(merch);
    }
    
    public Iterable<MerchSize> findAllMS() {
	return mss.findAllMerchSizes();
    }

    @Override
    public String findTypeNameByMerchID(Long merchID) {
	return ts.findById(ms.getTypeFKById(merchID)).get().getTitle();
    }

    @Override
    public Long getIdByTitle(String sectionTitle) {
	return ss.getIdByTitle(sectionTitle);
    }

    @Override
    public Iterable<Section> findAllSections() {
	return ss.findAll();
    }
    
}
