package com.artemius.dwshop.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "colour")
public class Colour {
    @Id
    @Column(name = "id_PK", nullable = false)
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    @Getter
    @Setter
    private long id_PK;
    
    @Column(name = "title", nullable = false)
    @Getter
    @Setter
    private String title;
    
    @Column(name = "code", nullable = false)
    @Getter
    @Setter
    private String code;
}
