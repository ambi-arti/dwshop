package com.artemius.dwshop.repositories;

import org.springframework.data.repository.CrudRepository;
import com.artemius.dwshop.entities.MerchColour;

public interface MerchColourRepository extends CrudRepository<MerchColour, Long> {
    public Iterable<MerchColour> getByMerchFK(Long merchFK);
}
