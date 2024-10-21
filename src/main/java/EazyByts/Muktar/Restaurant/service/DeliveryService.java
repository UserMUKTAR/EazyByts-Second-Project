package EazyByts.Muktar.Restaurant.service;


import EazyByts.Muktar.Restaurant.Enums.OrderStatus;
import EazyByts.Muktar.Restaurant.model.Delivery;
import EazyByts.Muktar.Restaurant.model.Order;
import EazyByts.Muktar.Restaurant.model.Users;
import EazyByts.Muktar.Restaurant.repository.DeliveryRepository;
import EazyByts.Muktar.Restaurant.repository.OrderRepository;
import EazyByts.Muktar.Restaurant.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DeliveryService {


    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Delivery assignDeliveryPersonnel(Long orderId, Long deliveryPersonnelId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        Users deliveryPersonnel = userRepository.findById(deliveryPersonnelId)
                .orElseThrow(() -> new RuntimeException("Delivery personnel not found"));

        Delivery delivery = new Delivery();
        delivery.setOrder(order);
        delivery.setDeliveryPersonnel(deliveryPersonnel);
        delivery.setDeliveryStatus(OrderStatus.OUT_FOR_DELIVERY); // Use existing enum value
        delivery.setDeliveryTime(LocalDateTime.now());

        // Update the order's status to OUT_FOR_DELIVERY
        order.setOrderStatus(OrderStatus.OUT_FOR_DELIVERY);

        return deliveryRepository.save(delivery);
    }

    @Transactional
    public Delivery updateDeliveryStatus(Long deliveryId, OrderStatus status) {
        if (status != OrderStatus.OUT_FOR_DELIVERY && status != OrderStatus.DELIVERED) {
            throw new IllegalArgumentException("Invalid delivery status");
        }

        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

        delivery.setDeliveryStatus(status);
        delivery.setDeliveryTime(LocalDateTime.now());

        // Update the associated order's status as well
        Order order = delivery.getOrder();
        order.setOrderStatus(status);

        return deliveryRepository.save(delivery);
    }
}
