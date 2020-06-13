package com.artemius.dwshop.services;

import java.util.List;
import java.util.Optional;

import com.artemius.dwshop.entities.Property;
import com.artemius.dwshop.entities.MerchProperty;

public interface PropertyService {
    
    public List<Property> getPropertysByMerchID(Long merchID);
    public List<MerchProperty> getMerchPropertysByMerchID(Long merchID);
    public Optional<Property> getPropertyByID(Long id_PK);
    public Optional<MerchProperty> getMerchPropertyByID(Long id_PK);
    public void addNewProperty(Property Property);
    public void addNewMerchProperty(MerchProperty merchProperty);

}
