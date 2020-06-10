package com.artemius.dwshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artemius.dwshop.entities.MerchMaterial;

public interface MerchMaterialRepository extends JpaRepository<MerchMaterial, Long> {
    

}