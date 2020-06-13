package com.artemius.dwshop.services;

import java.util.List;
import java.util.Optional;

import com.artemius.dwshop.entities.Merch;

public interface MerchService {
    public List<Merch> findAll();
    public Optional<Merch> findById(Long merchID);
    public List<Merch> findBySection(String section);
    public Merch addNewMerch(Merch merch);
    public String findTypeNameByMerchID(Long merchID);
}
