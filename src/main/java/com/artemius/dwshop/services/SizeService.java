package com.artemius.dwshop.services;

import java.util.List;
import java.util.Optional;

import com.artemius.dwshop.entities.MSize;
import com.artemius.dwshop.entities.MerchSize;

public interface SizeService {
    
    public List<MSize> getMSizesByMerchID(Long merchID);
    public List<MerchSize> getMerchSizesByMerchID(Long merchID);
    public Optional<MSize> getMSizeByID(Long id_PK);
    public Optional<MerchSize> getMerchSizeByID(Long id_PK);
    public void addNewMSize(MSize material);
    public void addNewMerchSize(MerchSize merchMaterial);

}