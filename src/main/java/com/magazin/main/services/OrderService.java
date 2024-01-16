///OrderService.java
package com.magazin.main.services;

import com.magazin.main.entities.Order;
import com.magazin.main.entities.Product;
import com.magazin.main.repositories.CourierRepository;
import com.magazin.main.repositories.OrderRepository;

import com.magazin.main.repositories.ProductRepository;
import com.magazin.main.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final CourierRepository cRepository;
    private final ProductRepository pRepository;
    private final UserRepository uRepository;

    public Order getOrder(UUID id) {
        return repository.findById(id).get();
    }

    public List<Order> getAllOrders(UUID adminId){
        String adminCheck = uRepository.findUserById(adminId).getRole();
        List<Order> errorThrow = new ArrayList<>();
//        errorThrow.add(,);
        if (adminCheck.equals("admin")){
            return repository.findAll();
        }
        else return errorThrow;
    }

    public Order setOrder(UUID userId, UUID productId, int quantity, UUID courierId){
        Product checkedProduct = pRepository.findProductById(productId);
        UUID newOrderId = UUID.randomUUID();
        double prodPrice = checkedProduct.getPrice();
        double courPrice = cRepository.findCourierById(courierId).getDelivery_price();
        double totalPrice = (prodPrice * quantity + courPrice);

        Order newOrder = new Order(newOrderId, userId, List.of(productId), quantity, courierId, totalPrice);
        return repository.save(newOrder);
    }

    public void eraseOrder(UUID userId, UUID orderId){
        UUID userCheckId = repository.findById(orderId).get().getUser_id();
        if (userId.equals(userCheckId)) {
            repository.deleteById(orderId);
        }
    }

    public void adminEraseOrder(UUID userId, UUID orderId){
        String adminCheck = uRepository.findUserById(userId).getRole();
        if (adminCheck.equals("admin")) {
            repository.deleteById(orderId);
        }
    }
}