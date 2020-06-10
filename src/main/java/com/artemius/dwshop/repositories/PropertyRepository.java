package com.artemius.dwshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artemius.dwshop.entities.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    

}