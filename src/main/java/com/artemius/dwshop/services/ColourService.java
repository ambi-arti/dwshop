package com.artemius.dwshop.services;

import java.util.List;
import java.util.Optional;

import com.artemius.dwshop.entities.Colour;
import com.artemius.dwshop.entities.MerchColour;

public interface ColourService {
    
    public List<Colour> getColoursByMerchID(Long merchID);
    public List<MerchColour> getMerchColoursByMerchID(Long merchID);
    public Optional<Colour> getColourByID(Long id_PK);
    public Optional<MerchColour> getMerchColourByID(Long id_PK);
    public Colour addNewColour(Colour Colour);
    public MerchColour addNewMerchColour(MerchColour merchColour);

}