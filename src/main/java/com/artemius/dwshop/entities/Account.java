package com.artemius.dwshop.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id_PK;
    
    @Column(name = "surname", nullable = false)
    @Getter
    @Setter
    private String surname;
    
    @Column(name = "firstname", nullable = false)
    @Getter
    @Setter
    private String firstname;
    
    @Column(name = "patronymic", nullable = true)
    @Getter
    @Setter
    private String patronymic;
    
    @Column(name = "birthdate", nullable = false)
    @Getter
    @Setter
    private String birthdate;
    
    @Column(name = "city", nullable = false)
    @Getter
    @Setter
    private String city;
    
    @Column(name = "address", nullable = false)
    @Getter
    @Setter
    private String address;
    
    @Column(name = "username", nullable = false)
    @Getter
    @Setter
    private String username;
    
    @Column(name = "password", nullable = false)
    @Getter
    @Setter
    private String password;
    
    @Column(name = "active", nullable = true)
    @Getter
    @Setter
    private Boolean active;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "roles")
    @Getter
    @Setter
    private Role roles;

    
}