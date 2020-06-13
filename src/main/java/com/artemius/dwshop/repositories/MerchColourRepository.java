package com.artemius.dwshop.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.artemius.dwshop.entities.MerchColour;

public interface MerchColourRepository extends CrudRepository<MerchColour, Long> {
    @Query("SELECT e FROM MerchColour e WHERE e.merchFK.id_PK = :merchID ")
    public Iterable<MerchColour> findAllByMerchID(@Param("merchID")Long merchID);
}
