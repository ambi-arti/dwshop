package com.artemius.dwshop.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private long id_PK;
    
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "item_fk",nullable = false)
    @Getter
    @Setter
    private CartItem itemFK;
    
    @Column(name = "date", nullable = false)
    @Getter
    @Setter
    private String date;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Getter
    @Setter
    private OrderStatus status;
    
    @Column(name = "comment", nullable = true)
    @Getter
    @Setter
    private String comment;

}
