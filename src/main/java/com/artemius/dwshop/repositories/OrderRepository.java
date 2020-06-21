package com.artemius.dwshop.repositories;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.artemius.dwshop.entities.CartItem;
import com.artemius.dwshop.entities.Order;
import com.artemius.dwshop.entities.OrderStatus;

public interface OrderRepository extends JpaRepository<Order,Long>{
    
    //@Query("select o from order a where o.item_fk.consumer_fk.username=:username")
    @Query(value = "SELECT * FROM orders o JOIN cartitem c ON o.item_fk = c.id_pk JOIN account a ON c.consumer_fk = a.id_pk WHERE username=:username",nativeQuery = true)
    public List<Order> findAllByUsername(@Param("username")String username);
    
    @Query(value = "SELECT * FROM orders o JOIN cartitem c "
    +"ON o.item_fk = c.id_pk"+" JOIN account a ON c.consumer_fk = a.id_pk "
	    +"WHERE username=:username "+"AND o.status != 'Доставлен' "
	    	+"AND o.status != 'DISCARDED'",nativeQuery = true)
    public List<Order> findAllUndeliveredByUsername(@Param("username")String username);
    
   // @Query("select o from order a where o.item_fk.consumer_fk.city=:city")
    @Query(value = "SELECT * FROM orders o JOIN cartitem c ON o.item_fk = c.id_pk JOIN account a ON c.consumer_fk = a.id_pk WHERE city=:city AND o.status='Ожидается'",nativeQuery = true)
    public List<Order> findAllByCity(@Param("city")String city);
    
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE orders SET status = :status WHERE id_pk = :id", nativeQuery = true)
    public void setStatusById(@Param("status")OrderStatus status, @Param("id")Long id);
}
