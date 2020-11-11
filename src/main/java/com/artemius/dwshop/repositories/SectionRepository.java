package com.artemius.dwshop.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.artemius.dwshop.entities.Section;

public interface SectionRepository extends CrudRepository<Section, Long> {
    
    //@Query(value = "SELECT * FROM MerchSize e WHERE e.merchFK.id_PK = :merchID ")
    @Query(value = "SELECT id_pk FROM section WHERE title = :sectionTitle ", nativeQuery=true)
    public Long getIdByTitle(@Param("sectionTitle")String sectionTitle);
    
}