package com.artemius.dwshop.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.artemius.dwshop.entities.Account;
import com.artemius.dwshop.entities.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    
    @Query(value="SELECT * FROM cartitem WHERE consumer_fk = :consumer AND discarded != true ORDER BY id_pk ASC", nativeQuery = true)
    public List<CartItem> findAllByConsumerFK(@Param("consumer")Account consumer);
    
    @Query(value="SELECT * FROM cartitem c RIGHT JOIN account a ON c.consumer_fk = a.id_pk WHERE username = :username AND discarded != true ORDER BY c.id_pk ASC", nativeQuery = true)
    public List<CartItem> findAllByUsername(@Param("username")String username);
    
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE cartitem SET discarded = true WHERE id_pk = :id", nativeQuery = true)
    public void removeById(@Param("id")Long id);

}