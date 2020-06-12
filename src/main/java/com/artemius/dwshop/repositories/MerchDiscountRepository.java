package com.artemius.dwshop.repositories;

import org.springframework.data.repository.CrudRepository;
import com.artemius.dwshop.entities.MerchDiscount;

public interface MerchDiscountRepository extends CrudRepository<MerchDiscount, Long> {
    public Iterable<MerchDiscount> getByMerchFK(Long merchFK);
}