///CourierService.java
package com.magazin.main.services;

import com.magazin.main.entities.Courier;
import com.magazin.main.entities.Order;
import com.magazin.main.repositories.CourierRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourierService {
    private final CourierRepository repository;
    public Courier getCourier(UUID id) {
        return repository.findById(id).get();
    }

    public List<Courier> getAllCouriers(){return repository.findAll();}
}