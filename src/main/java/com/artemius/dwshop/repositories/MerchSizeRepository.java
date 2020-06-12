package com.artemius.dwshop.repositories;

import org.springframework.data.repository.CrudRepository;
import com.artemius.dwshop.entities.MerchSize;

public interface MerchSizeRepository extends CrudRepository<MerchSize, Long> {
    
    public Iterable<MerchSize> getByMerchFK(Long merchFK);
    
}