package com.artemius.dwshop.services.imps;

import java.security.Principal;
import java.util.Map;

import javax.persistence.EntityManager;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artemius.dwshop.entities.Account;
import com.artemius.dwshop.entities.Merch;
import com.artemius.dwshop.entities.MerchColour;
import com.artemius.dwshop.entities.MerchDiscount;
import com.artemius.dwshop.entities.MerchProperty;
import com.artemius.dwshop.entities.MerchSize;
import com.artemius.dwshop.entities.Query;
import com.artemius.dwshop.repositories.AccountRepository;
import com.artemius.dwshop.repositories.ColourRepository;
import com.artemius.dwshop.repositories.DiscountRepository;
import com.artemius.dwshop.repositories.MSizeRepository;
import com.artemius.dwshop.repositories.MerchColourRepository;
import com.artemius.dwshop.repositories.MerchDiscountRepository;
import com.artemius.dwshop.repositories.MerchPropertyRepository;
import com.artemius.dwshop.repositories.MerchRepository;
import com.artemius.dwshop.repositories.MerchSizeRepository;
import com.artemius.dwshop.repositories.MerchTypeRepository;
import com.artemius.dwshop.repositories.PropertyRepository;
import com.artemius.dwshop.services.AccountService;
import com.artemius.dwshop.services.AdminiumService;


@Service
public class AdmService implements AdminiumService {
    
    @Autowired
    AccountRepository acc;
    @Autowired
    MerchRepository mer;
    @Autowired
    MerchSizeRepository msr;
    @Autowired
    MSizeRepository ssr;
    @Autowired
    MerchTypeRepository mtr;
    @Autowired
    MerchPropertyRepository mpr;
    @Autowired
    PropertyRepository prp;
    @Autowired
    ColourRepository clr;
    @Autowired
    MerchColourRepository mcs;
    @Autowired
    DiscountRepository dsr;
    @Autowired
    MerchDiscountRepository mdr;
    @Autowired
    EntityManager em;

    @Override
    public void accounts(Map<String, Object> model, Principal principal) {
	fillAccounts(model,principal);
    }
    
    @Override
    public void merch(Map<String, Object> model, Principal principal) {
	fillMerch(model,principal);	
    }
    
    public void removeAccount(Long itemId, Map<String, Object> model, Principal principal) {
	try {
	    if (acc.findById(itemId).isPresent())
		acc.removeByIdPK(itemId);
	}
	catch(Exception e) {
	   // model.put("error",e.getLocalizedMessage());
	}
	fillAccounts(model,principal);
	
    }
    
    

    @Override
    public void editAccount(Account a, Map<String, Object> model, Principal principal) {
	try {
	    Account toEdit = acc.findById(a.getId_PK()).get();
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
	fillAccounts(model,principal);

    }
    
    @Override
    public void newAccount(Account a, Map<String, Object> model, Principal principal) {
	try {
	    acc.saveAndFlush(a);
	}
	catch(Exception e) {
	    model.put("error",e.getLocalizedMessage());
	}
	fillAccounts(model,principal);
    }
    
    private void fillAccounts(Map<String, Object> model, Principal principal) {
	model.put("items",acc.findAll());
	model.put("user",acc.findByUsername(principal.getName()));
	model.put("currentItem","account");
    }
    
    private void fillMerch(Map<String, Object> model, Principal principal) {
	model.put("items",mer.findAll());
	model.put("types",mtr.findAll());
	model.put("user",acc.findByUsername(principal.getName()));
	model.put("currentItem","merch");
    }

    @Override
    public void editMerch(Merch m, Map<String, Object> model, Principal principal) {
	try {
	    Merch toEdit = mer.findById(m.getId_PK()).get();
	    toEdit.setTitle(m.getTitle());
	    toEdit.setTypeFK(m.getTypeFK());
	    toEdit.setImgsrc(m.getImgsrc());
	    toEdit.setSection(m.getSection());
	    toEdit.setPrice(m.getPrice());
	    toEdit.setDescription(m.getDescription());
	    toEdit.setScore(m.getScore());
	    toEdit.setMarks(m.getMarks());
	    mer.save(toEdit);
	}
	catch (Exception e) {
	    model.put("error",e.getLocalizedMessage());
	}
	fillMerch(model,principal);
    }

    @Override
    public void newMerch(Merch m, Map<String, Object> model, Principal principal) {
	try {
	    mer.save(m);
	}
	catch (Exception e) {
	    model.put("error",e.getLocalizedMessage());
	}
	fillMerch(model,principal);
    }

    @Override
    public void removeMerch(Long itemId, Map<String, Object> model, Principal principal) {
	try {
	    if (mer.findById(itemId).isPresent())
		mer.deleteById(itemId);
	}
	catch(Exception e) {
	   // model.put("error",e.getLocalizedMessage());
	}
	fillMerch(model,principal);
	
    }

    @Override
    public void merchsize(Map<String, Object> model, Principal principal) {
	model.put("merchList",mer.findAll());
	model.put("sizeList",ssr.findAll());
	model.put("items",msr.findAll());
	model.put("currentItem","merchsize");
    }

    @Override
    public void merchcolour(Map<String, Object> model, Principal principal) {
	model.put("merchList",mer.findAll());
	model.put("colours",clr.findAll());
	model.put("items",mcs.findAll());
	model.put("currentItem","merchcolour");
	
    }

    @Override
    public void merchdisc(Map<String, Object> model, Principal principal) {
	model.put("merchList",mer.findAll());
	model.put("discs",dsr.findAll());
	model.put("items",mdr.findAll());
	model.put("currentItem","merchdisc");
	
    }

    @Override
    public void merchprops(Map<String, Object> model, Principal principal) {
	model.put("merchList",mer.findAll());
	model.put("props",prp.findAll());
	model.put("items",mpr.findAll());
	model.put("currentItem","merchprops");
	
    }
    
    private void fillNative(Map<String, Object> model, Principal principal) {
	model.put("response","Enter your query here (not sure if it works): ");
    }

    @Override
    public void editMerchSize(MerchSize m, Map<String, Object> model, Principal principal) {
	MerchSize toEdit = msr.findById(m.getId_PK()).get();
	try {
	    toEdit.setMerchFK(m.getMerchFK());
	    toEdit.setSizeFK(m.getSizeFK());
	    toEdit.setQuantity(m.getQuantity());
	    msr.save(toEdit);
	}
	catch (Exception e) {
	    model.put("error",e.getLocalizedMessage());
	}
	merchsize(model,principal);
    }

    @Override
    public void newMerchSize(MerchSize m, Map<String, Object> model, Principal principal) {
	try {
	    msr.save(m);
	}
	catch (Exception e) {
	    model.put("error",e.getLocalizedMessage());
	}
	merchsize(model,principal);
	
    }

    @Override
    public void removeMerchSize(Long itemId, Map<String, Object> model, Principal principal) {
	try {
	    if (msr.findById(itemId).isPresent())
		msr.deleteById(itemId);
	}
	catch(Exception e) {
	   // model.put("error",e.getLocalizedMessage());
	}
	merchsize(model,principal);
	
    }

    @Override
    public void editMerchСolour(MerchColour m, Map<String, Object> model, Principal principal) {
	MerchColour toEdit = mcs.findById(m.getId_PK()).get();
	try {
	    toEdit.setMerchFK(m.getMerchFK());
	    toEdit.setColourFK(m.getColourFK());
	    mcs.save(toEdit);
	}
	catch (Exception e) {
	    model.put("error",e.getLocalizedMessage());
	}
	merchcolour(model,principal);
	
    }

    @Override
    public void newMerchColour(MerchColour m, Map<String, Object> model, Principal principal) {
	try {
	    mcs.save(m);
	}
	catch (Exception e) {
	    model.put("error",e.getLocalizedMessage());
	}
	merchcolour(model,principal);
	
    }

    @Override
    public void removeMerchColour(Long itemId, Map<String, Object> model, Principal principal) {
	try {
	    if (mcs.findById(itemId).isPresent())
		mcs.deleteById(itemId);
	}
	catch(Exception e) {
	   // model.put("error",e.getLocalizedMessage());
	}
	merchcolour(model,principal);
	
    }

    @Override
    public void editMerchDisc(MerchDiscount m, Map<String, Object> model, Principal principal) {
	MerchDiscount toEdit = mdr.findById(m.getId_PK()).get();
	try {
	    toEdit.setMerchFK(m.getMerchFK());
	    toEdit.setDiscountFK(m.getDiscountFK());
	    mdr.save(toEdit);
	}
	catch (Exception e) {
	    model.put("error",e.getLocalizedMessage());
	}
	merchdisc(model,principal);
	
    }

    @Override
    public void newMerchDisc(MerchDiscount m, Map<String, Object> model, Principal principal) {
	try {
	    mdr.save(m);
	}
	catch (Exception e) {
	    model.put("error",e.getLocalizedMessage());
	}
	merchdisc(model,principal);
	
    }

    @Override
    public void removeMerchDisc(Long itemId, Map<String, Object> model, Principal principal) {
	try {
	    if (mdr.findById(itemId).isPresent())
		mdr.deleteById(itemId);
	}
	catch(Exception e) {
	   // model.put("error",e.getLocalizedMessage());
	}
	merchdisc(model,principal);
	
    }

    @Override
    public void editMerchProps(MerchProperty m, Map<String, Object> model, Principal principal) {
	MerchProperty toEdit = mpr.findById(m.getId_PK()).get();
	try {
	    toEdit.setMerchFK(m.getMerchFK());
	    toEdit.setPropertyFK(m.getPropertyFK());
	    toEdit.setValue(m.getValue());
	    mpr.save(toEdit);
	}
	catch (Exception e) {
	    model.put("error",e.getLocalizedMessage());
	}
	merchprops(model,principal);
	
    }

    @Override
    public void newMerchProps(MerchProperty m, Map<String, Object> model, Principal principal) {
	try {
	    mpr.save(m);
	}
	catch (Exception e) {
	    model.put("error",e.getLocalizedMessage());
	}
	merchprops(model,principal);
	
    }

    @Override
    public void removeMerchProps(Long itemId, Map<String, Object> model, Principal principal) {
	try {
	    if (mpr.findById(itemId).isPresent())
		mpr.deleteById(itemId);
	}
	catch(Exception e) {
	   // model.put("error",e.getLocalizedMessage());
	}
	merchprops(model,principal);
	
    }
    

    @Override
    public void nativeQuery(Map<String, Object> model, Principal principal) {
	fillNative(model,principal);
	
    }

    @Override
    public void nativeSubmit(Query q, Map<String, Object> model, Principal principal) {
	try {
	    javax.persistence.Query query = em.createNativeQuery(q.getQuery());	    
	    List<Object[]> response = query.getResultList();
	    boolean hasResults=true;
	    String toReturn="Response:\n";
	    if (response==null) {
		hasResults=false;
		toReturn="The query didn't yield any results!";
	    }
	    if (hasResults) {
		for (int i=0;i<response.size();i++) {
		    toReturn+="#"+i+" {";
		    for (int j=0;j<response
			.get(i)
			.length;j++) {
				toReturn+="\n\t"+response.get(i)[j];
		    }
			toReturn+="\n}\n";
		}
	    }

	    model.put("response",toReturn); 
	}
	catch (Exception e) {
	    model.put("response",e.getLocalizedMessage());
	}

    }

    @Override
    public void mostPurchases(Map<String, Object> model, Principal principal) {
	model.put("merchList", mer.findAllOrderByPurchasesDesc());	
    }

    @Override
    public void leastPurchases(Map<String, Object> model, Principal principal) {
	model.put("merchList", mer.findAllOrderByPurchasesAsc());	
    }

    @Override
    public void mostPurchasesBySize(Map<String, Object> model, Principal principal) {
	model.put("merchSizeList", msr.findAllOrderByPurchasesDesc());	
    }

    @Override
    public void leastPurchasesBySize(Map<String, Object> model, Principal principal) {
	model.put("merchSizeList", msr.findAllOrderByPurchasesAsc());	
    }

    @Override
    public void mostRatingsBySize(Map<String, Object> model, Principal principal) {
	model.put("merchSizeList", mer.findAllOrderByScoresDesc());	
    }

    @Override
    public void leastRatingsBySize(Map<String, Object> model, Principal principal) {
	model.put("merchSizeList", mer.findAllOrderByScoresDesc());	
    }

    @Override
    public void avgPrices(Map<String, Object> model, Principal principal) {
	//let's leave it for the better times, I guess
	
    }

}
