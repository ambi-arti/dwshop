package com.artemius.dwshop.repositories;

import org.springframework.data.repository.CrudRepository;
import com.artemius.dwshop.entities.MerchProperty;

public interface MerchPropertyRepository extends CrudRepository<MerchProperty, Long> {
    
    public Iterable<MerchProperty> getByMerchFK(Long merchFK);
    
}