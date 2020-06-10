package com.artemius.dwshop.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "merch")
public class Merch {
    @Id
    @Column(name = "id_PK", nullable = false)
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    private long id_PK;
    
    @Column(name = "title", nullable = false)
    private String title;
    
    @OneToOne
    @JoinColumn(name = "type_FK",nullable = false)
    private long typeFK;
    
    @Column(name = "price", nullable = false)
    private long price;
    
    @Column(name = "imgsrc", nullable = false)
    private String imgsrc;
    
    @Column(name = "section", nullable = false)
    private String section;
    
    @Column(name = "description", nullable = false)
    private String description;
}
