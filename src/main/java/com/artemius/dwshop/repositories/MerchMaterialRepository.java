package com.artemius.dwshop.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.artemius.dwshop.entities.Merch;
import com.artemius.dwshop.entities.MerchMaterial;

public interface MerchMaterialRepository extends CrudRepository<MerchMaterial, Long> {
    
    @Query("SELECT e FROM MerchMaterial e WHERE e.merchFK.id_PK = :merchID ")
    public Iterable<MerchMaterial> findAllByMerchID(@Param("merchID")Long merchID);

}