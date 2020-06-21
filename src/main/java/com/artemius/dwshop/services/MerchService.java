package com.artemius.dwshop.services;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.artemius.dwshop.entities.Merch;

public interface MerchService {
    public List<Merch> findAll();
    public Optional<Merch> findById(Long merchID);
    public List<Merch> findBySection(String section);
    public Merch addNewMerch(Merch merch);
    public String findTypeNameByMerchID(Long merchID);
    public void casual(Map<String,Object> model, Principal principal);
    public void merchInfo(Long merchID, Map<String,Object> model);
}
