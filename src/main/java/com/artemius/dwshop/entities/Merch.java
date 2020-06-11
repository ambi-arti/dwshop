package com.artemius.dwshop.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "merch")
public class Merch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private long id_PK;
    
    @Column(name = "title", nullable = false)
    @Getter
    @Setter
    private String title;
    
    @ManyToOne
    @JoinColumn(name = "type_FK",nullable = false)
    @Getter
    @Setter
    private MerchType typeFK;
    
    @Column(name = "price", nullable = false)
    @Getter
    @Setter
    private long price;
    
    @Column(name = "imgsrc", nullable = false)
    @Getter
    @Setter
    private String imgsrc;
    
    @Column(name = "section", nullable = false)
    @Getter
    @Setter
    private String section;
    
    @Column(name = "description", nullable = false)
    @Getter
    @Setter
    private String description;
}
