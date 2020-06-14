package com.artemius.dwshop.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.artemius.dwshop.entities.MerchSize;

public interface MerchSizeRepository extends CrudRepository<MerchSize, Long> {
    
    //@Query(value = "SELECT * FROM MerchSize e WHERE e.merchFK.id_PK = :merchID ")
    @Query(value = "SELECT * FROM MerchSize WHERE merch_FK = :merchID ", nativeQuery=true)
    public Iterable<MerchSize> findAllByMerchID(@Param("merchID")Long merchID);
    
}