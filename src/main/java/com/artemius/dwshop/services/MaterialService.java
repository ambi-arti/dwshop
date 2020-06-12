package com.artemius.dwshop.services;

import java.util.List;
import java.util.Optional;

import com.artemius.dwshop.entities.Material;
import com.artemius.dwshop.entities.MerchMaterial;

public interface MaterialService {
    
    public List<Material> getMaterialsByMerchID(Long merchID);
    public List<MerchMaterial> getMerchMaterialsByMerchID(Long merchID);
    public Optional<Material> getMaterialByID(Long id_PK);
    public Optional<MerchMaterial> getMerchMaterialByID(Long id_PK);
    public void addNewMaterial(Material material);
    public void addNewMerchMaterial(MerchMaterial merchMaterial);

}
