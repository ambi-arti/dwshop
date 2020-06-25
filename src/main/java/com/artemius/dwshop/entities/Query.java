package com.artemius.dwshop.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Query {
    
    @Id
    private Long id;
    
    @Getter
    @Setter
    private String query;
}
