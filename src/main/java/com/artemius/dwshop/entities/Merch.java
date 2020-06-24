package com.artemius.dwshop.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

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
    private Long id_PK;
    
    @Column(name = "title", nullable = false)
    @Getter
    @Setter
    private String title;
    
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "type_fk",nullable = false)
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
    
    @Column(name = "score", nullable = false)
    @Getter
    @Setter
    private Long score;
    
    @Column(name = "marks", nullable = false)
    @Getter
    @Setter
    private Long marks;
    
    @Generated(GenerationTime.ALWAYS)
    @Column(name = "overall", nullable = false)
    @Getter
    private Double overall;
}
