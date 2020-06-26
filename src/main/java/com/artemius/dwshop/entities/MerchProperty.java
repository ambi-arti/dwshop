package com.artemius.dwshop.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "merchproperty")
public class MerchProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private long id_PK;
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "merch_FK",nullable = false)
    @Getter
    @Setter
    private Merch merchFK;
    
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "property_FK",nullable = false)
    @Getter
    @Setter
    private Property propertyFK;
    
    @Column(name = "value", nullable = false)
    @Getter
    @Setter
    private String value;
    
}