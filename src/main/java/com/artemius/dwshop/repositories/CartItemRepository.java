package com.artemius.dwshop.repositories;

import org.springframework.data.repository.CrudRepository;

import com.artemius.dwshop.entities.CartItem;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {
    

}