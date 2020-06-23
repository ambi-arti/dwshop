package com.artemius.dwshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.artemius.dwshop.entities.Account;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    public Account findByUsername(String username);
    @Query(value = "DELETE FROM account a WHERE a.id_pk=:id_PK",nativeQuery=true)
    public Account removeByIdPK(@Param(value="id_PK")Long id_PK);
    public List<Account> findAllByCity(String city);
}