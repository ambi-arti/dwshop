package com.artemius.dwshop.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.artemius.dwshop.entities.MerchDiscount;

public interface MerchDiscountRepository extends CrudRepository<MerchDiscount, Long> {
    @Query("SELECT e FROM MerchDiscount e WHERE e.merchFK.id_PK = :merchID ")
    public Iterable<MerchDiscount> findAllByMerchID(@Param("merchID")Long merchID);
}