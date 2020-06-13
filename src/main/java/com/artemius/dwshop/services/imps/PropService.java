package com.artemius.dwshop.services.imps;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.artemius.dwshop.entities.Property;
import com.artemius.dwshop.entities.MerchProperty;
import com.artemius.dwshop.repositories.PropertyRepository;
import com.artemius.dwshop.repositories.MerchPropertyRepository;

@Service
public class PropService implements com.artemius.dwshop.services.PropertyService {
    
    @Autowired
    private MerchPropertyRepository mM;
    @Autowired
    private PropertyRepository m;

    @Override
    public List<Property> getPropertysByMerchID(Long merchID) {
	Iterable<MerchProperty> merchMats =  getMerchPropertysByMerchID(merchID);
	List<Long> ids = new ArrayList<Long>();
	for (MerchProperty merchMat: merchMats) {
	    ids.add(merchMat.getPropertyFK().getId_PK());
	}
	List<Property> toReturn = new ArrayList<>();
	for(Property e :m.findAllById(ids))
	    toReturn.add(e);
	return toReturn;
    }

    @Override
    public List<MerchProperty> getMerchPropertysByMerchID(Long merchID) {
	List<MerchProperty> toReturn = new ArrayList<>();
	for (MerchProperty e: mM.findAllByMerchID(merchID))
	    toReturn.add(e);
	return toReturn;
    }

    @Override
    public Optional<Property> getPropertyByID(Long id_PK) {
	return m.findById(id_PK);
    }

    @Override
    public Optional<MerchProperty> getMerchPropertyByID(Long id_PK) {
	return mM.findById(id_PK);
    }

    @Override
    public void addNewProperty(Property Property) {
	m.save(Property);
    }

    @Override
    public void addNewMerchProperty(MerchProperty merchProperty) {
	mM.save(merchProperty);
    }
}