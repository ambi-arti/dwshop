package com.artemius.dwshop.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.artemius.dwshop.entities.City;
import com.artemius.dwshop.entities.Region;

public interface CityRepository extends CrudRepository<City,Long>{
    
    public Iterable<City> findAllByRegion(Optional<Region> optional);

}
