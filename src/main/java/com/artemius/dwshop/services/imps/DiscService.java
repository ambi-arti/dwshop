package com.artemius.dwshop.services.imps;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artemius.dwshop.entities.Discount;
import com.artemius.dwshop.entities.MerchDiscount;
import com.artemius.dwshop.repositories.DiscountRepository;
import com.artemius.dwshop.repositories.MerchDiscountRepository;

@Service
public class DiscService implements com.artemius.dwshop.services.DiscountService {
    
    @Autowired
    private MerchDiscountRepository mM;
    @Autowired
    private DiscountRepository m;

    @Override
    public List<Discount> getDiscountsByMerchID(Long merchID) {
	Iterable<MerchDiscount> merchMats =  getMerchDiscountsByMerchID(merchID);
	List<Long> ids = new ArrayList<Long>();
	for (MerchDiscount merchMat: merchMats) {
	    ids.add(merchMat.getDiscountFK().getId_PK());
	}
	List<Discount> toReturn = new ArrayList<>();
	for(Discount e :m.findAllById(ids))
	    toReturn.add(e);
	return toReturn;
    }

    @Override
    public List<MerchDiscount> getMerchDiscountsByMerchID(Long merchID) {
	List<MerchDiscount> toReturn = new ArrayList<>();
	for (MerchDiscount e: mM.findAllByMerchID(merchID))
	    toReturn.add(e);
	return toReturn;
    }

    @Override
    public Optional<Discount> getDiscountByID(Long id_PK) {
	return m.findById(id_PK);
    }

    @Override
    public Optional<MerchDiscount> getMerchDiscountByID(Long id_PK) {
	return mM.findById(id_PK);
    }

    @Override
    public void addNewDiscount(Discount Discount) {
	m.save(Discount);
    }

    @Override
    public void addNewMerchDiscount(MerchDiscount merchDiscount) {
	mM.save(merchDiscount);
    }
}