package com.artemius.dwshop.services;

import java.util.List;
import java.util.Optional;

import com.artemius.dwshop.entities.Discount;
import com.artemius.dwshop.entities.MerchDiscount;

public interface DiscountService {
    
    public List<Discount> getDiscountsByMerchID(Long merchID);
    public List<MerchDiscount> getMerchDiscountsByMerchID(Long merchID);
    public Optional<Discount> getDiscountByID(Long id_PK);
    public Optional<MerchDiscount> getMerchDiscountByID(Long id_PK);
    public void addNewDiscount(Discount Discount);
    public void addNewMerchDiscount(MerchDiscount merchDiscount);

}