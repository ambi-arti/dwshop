package com.artemius.dwshop.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "deps")
public class Deps {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id_PK;
 
    @Column
    @Getter
    @Setter
    private String name;
    
}
