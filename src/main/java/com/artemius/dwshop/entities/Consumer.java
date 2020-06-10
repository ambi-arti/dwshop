package com.artemius.dwshop.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "consumer")
public class Consumer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private long id_PK;
    
    @Column(name = "surname", nullable = false)
    @Getter
    @Setter
    private String surname;
    
    @Column(name = "firstName", nullable = false)
    @Getter
    @Setter
    private String firstName;
    
    @Column(name = "patronymic", nullable = true)
    @Getter
    @Setter
    private String patronymic;
    
    @Column(name = "birthday", nullable = false)
    @Getter
    @Setter
    private String birthday;
    
    @Column(name = "city", nullable = false)
    @Getter
    @Setter
    private String city;
    
    @Column(name = "address", nullable = false)
    @Getter
    @Setter
    private String address;
    
}