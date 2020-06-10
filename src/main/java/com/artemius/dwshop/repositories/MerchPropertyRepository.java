package com.artemius.dwshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artemius.dwshop.entities.MerchProperty;

public interface MerchPropertyRepository extends JpaRepository<MerchProperty, Long> {
    

}