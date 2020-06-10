package com.artemius.dwshop.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "deps")
public class Emps {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id_PK;
 
    @Column
    @Setter
    @Getter
    private String firstName;
    
    @Column
    @Setter
    @Getter
    private String lastName;
    
    @Column
    @Setter
    @Getter
    private Long dept;
    
}
