package com.artemius.dwshop.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "consumer")
public class Consumer {
    @Id
    @Column(name = "id_PK", nullable = false)
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    private long id_PK;
    
    @Column(name = "surname", nullable = false)
    private String surname;
    
    @Column(name = "surname", nullable = false)
    private String firstName;
    
    @Column(name = "patronymic", nullable = true)
    private String patronymic;
    
    @Column(name = "birthday", nullable = false)
    private String birthday;
    
    @Column(name = "city", nullable = false)
    private String city;
    
    @Column(name = "address", nullable = false)
    private String address;
    
}