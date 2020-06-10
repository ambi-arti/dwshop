package com.artemius.dwshop.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "merchmaterial")
public class MerchMaterial {
    @Id
    @Column(name = "id_PK", nullable = false)
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    private long id_PK;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "merch_FK",nullable = false)
    private long merchFK;
    
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "material_FK",nullable = false)
    private long materialFK;

    
}