package com.artemius.dwshop.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.artemius.dwshop.entities.Merch;
import com.artemius.dwshop.entities.MerchType;

public interface MerchRepository extends CrudRepository<Merch, Long> {
    public Iterable<Merch> findAllBySection(String section);
  //  @Query(value = "SELECT type_FK FROM Merch e WHERE e.id_PK = :merchID", nativeQuery = true)
    @Query("SELECT e.typeFK.id_PK FROM Merch e WHERE e.id_PK = :merchID ")
    public Long getTypeFKById(@Param("merchID")Long merchID);
}
