///VendorRepository.java
package com.magazin.main.repositories;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magazin.main.entities.Vendor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, UUID>{
    Vendor findVendorById(@Param("id")  UUID id);
}