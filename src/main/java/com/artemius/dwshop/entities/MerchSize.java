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
@Table(name = "merchsize")
public class MerchSize {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private long id_PK;
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "merch_fk", nullable = false)
    @Getter
    @Setter
    private Merch merchFK;
    
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "size_fk",nullable = false)
    @Getter
    @Setter
    private MSize sizeFK;
    
    @Column(name = "quantity", nullable = false)
    @Getter
    @Setter
    private long quantity;
    
    @Column(name = "purchases", nullable = false)
    @Getter
    @Setter
    private Long purchases;
    
}