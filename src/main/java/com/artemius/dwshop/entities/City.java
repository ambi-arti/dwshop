package com.artemius.dwshop.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;

@Data
@Entity
@Table(name = "_cities")
public class City {
    @Id
    @Getter
    private Long city_id;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "region_id",nullable = false)
    @Getter
    private Region region;
    
    @Column(name = "title_ru", nullable = false)
    @Getter
    private String Title;
}
