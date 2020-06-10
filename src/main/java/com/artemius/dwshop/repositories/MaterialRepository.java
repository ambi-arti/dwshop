package com.artemius.dwshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artemius.dwshop.entities.Material;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    

}