///CourierRepository.java
package com.magazin.main.repositories;

import java.util.*;

import com.magazin.main.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import com.magazin.main.entities.Courier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRepository extends JpaRepository<Courier, UUID>{
    Courier findCourierById(@Param("id")  UUID id);

    List<Courier> findAll();
}