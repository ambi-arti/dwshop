package com.artemius.dwshop.services.imps;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artemius.dwshop.entities.Merch;
import com.artemius.dwshop.repositories.MerchRepository;
import com.artemius.dwshop.repositories.MerchTypeRepository;
import com.artemius.dwshop.services.MerchService;

@Service
public class MerService implements MerchService {

    @Autowired
    MerchRepository ms;
    @Autowired
    MerchTypeRepository ts;

    @Override
    public List<Merch> findAll() {
	List<Merch> toReturn = new ArrayList<>();
	for (Merch m: ms.findAll() ) {
	    toReturn.add(m);
	}
	return toReturn;
    }

    @Override
    public Optional<Merch> findById(Long merchID) {
	return ms.findById(merchID);
    }

    @Override
    public List<Merch> findBySection(String section) {
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

    @Override
    public String findTypeNameByMerchID(Long merchID) {
	return ts.findById(ms.getTypeFKById(merchID)).get().getTitle();
    }
    
}
