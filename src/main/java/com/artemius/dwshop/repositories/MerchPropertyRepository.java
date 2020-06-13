package com.artemius.dwshop.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.artemius.dwshop.entities.MerchProperty;

public interface MerchPropertyRepository extends CrudRepository<MerchProperty, Long> {
    
    @Query("SELECT e FROM MerchProperty e WHERE e.merchFK.id_PK = :merchID ")
    public Iterable<MerchProperty> findAllByMerchID(@Param("merchID")Long merchID);
    
}