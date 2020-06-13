package com.artemius.dwshop.services.imps;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artemius.dwshop.entities.Colour;
import com.artemius.dwshop.entities.MerchColour;
import com.artemius.dwshop.repositories.ColourRepository;
import com.artemius.dwshop.repositories.MerchColourRepository;

@Service
public class ColService implements com.artemius.dwshop.services.ColourService {
    
    @Autowired
    private MerchColourRepository mM;
    @Autowired
    private ColourRepository m;

    @Override
    public List<Colour> getColoursByMerchID(Long merchID) {
	Iterable<MerchColour> merchMats =  getMerchColoursByMerchID(merchID);
	List<Long> ids = new ArrayList<Long>();
	for (MerchColour merchMat: merchMats) {
	    ids.add(merchMat.getColourFK().getId_PK());
	}
	List<Colour> toReturn = new ArrayList<>();
	for(Colour e :m.findAllById(ids))
	    toReturn.add(e);
	return toReturn;
    }

    @Override
    public List<MerchColour> getMerchColoursByMerchID(Long merchID) {
	List<MerchColour> toReturn = new ArrayList<>();
	for (MerchColour e: mM.findAllByMerchID(merchID))
	    toReturn.add(e);
	return toReturn;
    }

    @Override
    public Optional<Colour> getColourByID(Long id_PK) {
	return m.findById(id_PK);
    }

    @Override
    public Optional<MerchColour> getMerchColourByID(Long id_PK) {
	return mM.findById(id_PK);
    }

    @Override
    public void addNewColour(Colour Colour) {
	m.save(Colour);
    }

    @Override
    public void addNewMerchColour(MerchColour merchColour) {
	mM.save(merchColour);
    }
}