package com.artemius.dwshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artemius.dwshop.entities.Colour;

public interface ColourRepository extends JpaRepository<Colour, Long> {
    

}
