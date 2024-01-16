///VendorService.java
package com.magazin.main.services;

import com.magazin.main.entities.Vendor;
import com.magazin.main.repositories.VendorRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VendorService {
    private final VendorRepository repository;
    public Vendor getVendor(UUID id) {
        return repository.findById(id).get();
    }
}