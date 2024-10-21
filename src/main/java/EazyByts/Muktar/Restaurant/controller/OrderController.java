package EazyByts.Muktar.Restaurant.controller;

import EazyByts.Muktar.Restaurant.Enums.OrderStatus;
import EazyByts.Muktar.Restaurant.dto.OrderItemDto;
import EazyByts.Muktar.Restaurant.model.Order;
import EazyByts.Muktar.Restaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;


    //POST /orders/place
    @PostMapping("/placeOrder/{customerId}")
//    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Order> placeOrder(@RequestParam Long customerId, @RequestBody List<OrderItemDto> orderItemsDto) {
        Order newOrder = orderService.placeOrder(customerId, orderItemsDto);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    //GET /orders/customer/{customerId}
    @GetMapping("/getOrder/{customerId}")
//    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<Order>> getCustomerOrders(@PathVariable Long customerId) {
        List<Order> orders = orderService.getOrdersByCustomer(customerId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

//    PUT /orders/{orderId}/status
    @PutMapping("/{orderId}/status")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('DELIVERY_PERSONNEL')")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long orderId, @RequestParam OrderStatus status) {
        Order updatedOrder = orderService.updateOrderStatus(orderId, status);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }
}
