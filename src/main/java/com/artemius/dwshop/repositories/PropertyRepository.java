package com.artemius.dwshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.artemius.dwshop.entities.Property;

public interface PropertyRepository extends CrudRepository<Property, Long> {
    

}