package EazyByts.Muktar.Restaurant.repository;

import EazyByts.Muktar.Restaurant.model.Delivery;
import EazyByts.Muktar.Restaurant.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,Long> {
    List<Delivery> findByDeliveryPersonnel(Users deliveryPersonnel);


}
