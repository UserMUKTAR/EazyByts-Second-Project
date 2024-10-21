package EazyByts.Muktar.Restaurant.model;


import EazyByts.Muktar.Restaurant.Enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "delivery")
public class Delivery {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order; // Each delivery is linked to a specific order

    @Enumerated(EnumType.STRING)
    private OrderStatus deliveryStatus; // Reusing OrderStatus enum

    private LocalDateTime deliveryTime;

    @ManyToOne
    @JoinColumn(name = "delivery_personnel_id")
    private Users deliveryPersonnel; // The user assigned to deliver the order

    // Getters and setters


    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(OrderStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Users getDeliveryPersonnel() {
        return deliveryPersonnel;
    }

    public void setDeliveryPersonnel(Users deliveryPersonnel) {
        this.deliveryPersonnel = deliveryPersonnel;
    }
}
