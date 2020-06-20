package com.artemius.dwshop.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

//it's a virtual entity meant for storing the account's edited information
@Entity
public class EditedAccount {
    @Id
    @Getter
    @Setter
    private Long id_PK;
    
    @Getter
    @Setter
    private String surname;
    
    @Getter
    @Setter
    private String firstname;
    
    @Getter
    @Setter
    private String patronymic;
    
    @Getter
    @Setter
    private String birthdate;
    
    @Getter
    @Setter
    private String city;
    
    @Getter
    @Setter
    private String address;
    
    @Getter
    @Setter
    private String confirm;
    
    @Getter
    @Setter
    private String password;
}
