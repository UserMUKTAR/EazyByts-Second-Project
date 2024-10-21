package EazyByts.Muktar.Restaurant.repository;

import EazyByts.Muktar.Restaurant.model.Order;
import EazyByts.Muktar.Restaurant.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer(Users customer); // Get orders by customer
}

