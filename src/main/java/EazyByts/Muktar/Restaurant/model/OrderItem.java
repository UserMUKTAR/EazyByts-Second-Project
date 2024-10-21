package EazyByts.Muktar.Restaurant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order; // Link to the parent order

    @ManyToOne
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem; // The menu item being ordered

    private int quantity;

    // Constructors, getters, and setters


    public OrderItem(Long orderItemId, Order order, MenuItem menuItem, int quantity) {
        this.orderItemId = orderItemId;
        this.order = order;
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public OrderItem() {

    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

