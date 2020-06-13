package com.artemius.dwshop.services.imps;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artemius.dwshop.entities.MSize;
import com.artemius.dwshop.entities.MerchSize;
import com.artemius.dwshop.repositories.MSizeRepository;
import com.artemius.dwshop.repositories.MerchSizeRepository;

@Service
public class MSizeService implements com.artemius.dwshop.services.SizeService {
    
    @Autowired
    private MerchSizeRepository mM;
    @Autowired
    private MSizeRepository m;

    @Override
    public List<MSize> getMSizesByMerchID(Long merchID) {
	Iterable<MerchSize> merchMats =  getMerchSizesByMerchID(merchID);
	List<Long> ids = new ArrayList<Long>();
	for (MerchSize merchMat: merchMats) {
	    ids.add(merchMat.getSizeFK().getId_PK());
	}
	List<MSize> toReturn = new ArrayList<>();
	for(MSize e :m.findAllById(ids))
	    toReturn.add(e);
	return toReturn;
    }

    @Override
    public List<MerchSize> getMerchSizesByMerchID(Long merchID) {
	List<MerchSize> toReturn = new ArrayList<>();
	for (MerchSize e: mM.findAllByMerchID(merchID))
	    toReturn.add(e);
	return toReturn;
    }

    @Override
    public Optional<MSize> getMSizeByID(Long id_PK) {
	return m.findById(id_PK);
    }

    @Override
    public Optional<MerchSize> getMerchSizeByID(Long id_PK) {
	return mM.findById(id_PK);
    }

    @Override
    public void addNewMSize(MSize MSize) {
	m.save(MSize);
    }

    @Override
    public void addNewMerchSize(MerchSize merchSize) {
	mM.save(merchSize);
    }
}