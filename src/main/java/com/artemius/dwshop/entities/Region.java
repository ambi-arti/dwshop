package com.artemius.dwshop.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;

@Data
@Entity
@Table(name = "_regions")
public class Region {
    
    @Id
    @Getter
    private Long region_id;
    
    @Column(name = "title_ru", nullable = false)
    @Getter
    private String Title;
    
    @Column(name = "country_id", nullable = false)
    @Getter
    private Long countryId;
}
