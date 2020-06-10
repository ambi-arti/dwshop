package com.artemius.dwshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artemius.dwshop.entities.Consumer;

public interface ConsumerRepository extends JpaRepository<Consumer, Long> {
    

}