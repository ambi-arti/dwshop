package com.artemius.dwshop.services.imps;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artemius.dwshop.entities.Material;
import com.artemius.dwshop.entities.MerchMaterial;
import com.artemius.dwshop.repositories.MaterialRepository;
import com.artemius.dwshop.repositories.MerchMaterialRepository;

@Service
public class MatService implements com.artemius.dwshop.services.MaterialService {
    
    @Autowired
    private MerchMaterialRepository mM;
    @Autowired
    private MaterialRepository m;

    @Override
    public List<Material> getMaterialsByMerchID(Long merchID) {
	Iterable<MerchMaterial> merchMats =  getMerchMaterialsByMerchID(merchID);
	List<Long> ids = new ArrayList<Long>();
	for (MerchMaterial merchMat: merchMats) {
	    ids.add(merchMat.getMaterialFK().getId_PK());
	}
	List<Material> toReturn = new ArrayList<>();
	for(Material e :m.findAllById(ids))
	    toReturn.add(e);
	return toReturn;
    }

    @Override
    public List<MerchMaterial> getMerchMaterialsByMerchID(Long merchID) {
	List<MerchMaterial> toReturn = new ArrayList<>();
	for (MerchMaterial e: mM.findAllByMerchID(merchID))
	    toReturn.add(e);
	return toReturn;
    }

    @Override
    public Optional<Material> getMaterialByID(Long id_PK) {
	return m.findById(id_PK);
    }

    @Override
    public Optional<MerchMaterial> getMerchMaterialByID(Long id_PK) {
	return mM.findById(id_PK);
    }

    @Override
    public Material addNewMaterial(Material material) {
	return m.save(material);
    }

    @Override
    public MerchMaterial addNewMerchMaterial(MerchMaterial merchMaterial) {
	return mM.save(merchMaterial);
    }

}
