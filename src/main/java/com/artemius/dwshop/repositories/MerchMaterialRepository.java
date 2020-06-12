package com.artemius.dwshop.repositories;

import org.springframework.data.repository.CrudRepository;
import com.artemius.dwshop.entities.MerchMaterial;

public interface MerchMaterialRepository extends CrudRepository<MerchMaterial, Long> {
    
    public Iterable<MerchMaterial> getByMerchFK(Long merchFK);

}