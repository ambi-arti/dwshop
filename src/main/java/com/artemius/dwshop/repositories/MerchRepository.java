package com.artemius.dwshop.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.artemius.dwshop.entities.Merch;
import com.artemius.dwshop.entities.MerchType;

public interface MerchRepository extends CrudRepository<Merch, Long> {
    public Iterable<Merch> findAllBySection(Long section);
  //  @Query(value = "SELECT type_FK FROM Merch e WHERE e.id_PK = :merchID", nativeQuery = true)
    @Query("SELECT e.typeFK.id_PK FROM Merch e WHERE e.id_PK = :merchID ")
    public Long getTypeFKById(@Param("merchID")Long merchID);
    @Query(value = "SELECT * FROM Merch WHERE section = :section ORDER BY title ASC", nativeQuery=true)
    public Iterable<Merch> findAllBySectionOrderByTitleAsc(@Param("section")Long section);
    @Query(value = "SELECT * FROM Merch WHERE section = :section ORDER BY title DESC", nativeQuery=true)
    public Iterable<Merch> findAllBySectionOrderByTitleDesc(@Param("section")Long section);
    @Query(value = "SELECT * FROM Merch WHERE section = :section ORDER BY price ASC", nativeQuery=true)
    public Iterable<Merch> findAllBySectionOrderByPriceAsc(@Param("section")Long section);
    @Query(value = "SELECT * FROM Merch WHERE section = :section ORDER BY price DESC", nativeQuery=true)
    public Iterable<Merch> findAllBySectionOrderByPriceDesc(@Param("section")Long section);
    @Query(value = "SELECT * FROM Merch WHERE section = :section ORDER BY score ASC", nativeQuery=true)
    public Iterable<Merch> findAllBySectionOrderByScoreAsc(@Param("section")Long section);
    @Query(value = "SELECT * FROM Merch WHERE section = :section ORDER BY score DESC", nativeQuery=true)
    public Iterable<Merch> findAllBySectionOrderByScoreDesc(@Param("section")Long section);
    
}
