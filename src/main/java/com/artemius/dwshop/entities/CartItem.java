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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "cartitem")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private long id_PK;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "merch_fk",nullable = false)
    @Getter
    @Setter
    private Merch merchFK;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "merchsize_fk",nullable = false)
    @Getter
    @Setter
    private MerchSize merchSizeFK;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "consumer_fk",nullable = false)
    @Getter
    @Setter
    private Account consumerFK;
    
    @Column(name = "quantity", nullable = false)
    @Getter
    @Setter
    private Long quantity;
    
    @Column(name = "cost", nullable = false)
    @Getter
    @Setter
    private Double cost;
    
    @Column(name = "discarded")
    @Getter
    @Setter
    private Boolean discarded;
}