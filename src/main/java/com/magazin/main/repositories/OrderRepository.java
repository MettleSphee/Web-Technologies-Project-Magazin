///OrderRepository.java
package com.magazin.main.repositories;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magazin.main.entities.Order;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID>{
    Order findOrderById(@Param("id")  UUID id);

    List<Order> findAll();


    /*
    Order createOrder(
            @Param("userId") UUID userId,
            @Param("productId") UUID productId,
            @Param("quantity") int quantity,
            @Param("courier") UUID courierId
    );
    */
}