package com.artemius.dwshop.repositories;

import org.springframework.data.repository.CrudRepository;
import com.artemius.dwshop.entities.Region;

public interface RegionRepository extends CrudRepository<Region, Long> {

    public Iterable<Region> findAllByCountryId(Long countryId);
}
