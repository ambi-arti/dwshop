package com.artemius.dwshop.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.artemius.dwshop.entities.Account;
import com.artemius.dwshop.entities.CartItem;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {
    
    public List<CartItem> findAllByConsumerFK(Account consumer);

}