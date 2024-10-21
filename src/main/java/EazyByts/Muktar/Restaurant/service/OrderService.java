package EazyByts.Muktar.Restaurant.service;


import EazyByts.Muktar.Restaurant.Enums.OrderStatus;
import EazyByts.Muktar.Restaurant.dto.OrderItemDto;
import EazyByts.Muktar.Restaurant.model.MenuItem;
import EazyByts.Muktar.Restaurant.model.Order;
import EazyByts.Muktar.Restaurant.model.OrderItem;
import EazyByts.Muktar.Restaurant.model.Users;
import EazyByts.Muktar.Restaurant.repository.MenuItemRepository;
import EazyByts.Muktar.Restaurant.repository.OrderRepository;
import EazyByts.Muktar.Restaurant.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {



    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private UserRepository userRepository;


    @Transactional
    public Order placeOrder(Long customerId, List<OrderItemDto> orderItemsDto) {
        Users customer = userRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Create a new order
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.PLACED);


        // Process each order item
        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (OrderItemDto itemDto : orderItemsDto) {
            MenuItem menuItem = menuItemRepository.findById(itemDto.getMenuItemId())
                    .orElseThrow(() -> new RuntimeException("Menu item not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setMenuItem(menuItem);
            orderItem.setQuantity(itemDto.getQuantity());
            orderItem.setOrder(order);


            orderItems.add(orderItem);
            totalPrice = totalPrice.add(menuItem.getPrice().multiply(new BigDecimal(itemDto.getQuantity())));
        }

        order.setOrderItems(orderItems);
        order.setTotalPrice(totalPrice);

        return orderRepository.save(order); // Save order and order items
    }

    public List<Order> getOrdersByCustomer(Long customerId) {
        Users customer = userRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return orderRepository.findByCustomer(customer);
    }

    public Order updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setOrderStatus(newStatus);
        return orderRepository.save(order);
    }


}
