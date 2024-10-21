package EazyByts.Muktar.Restaurant.controller;

import EazyByts.Muktar.Restaurant.Enums.OrderStatus;
import EazyByts.Muktar.Restaurant.model.Delivery;
import EazyByts.Muktar.Restaurant.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;



//    POST /deliveries/assign?orderId=123&deliveryPersonnelId=456
    @PostMapping("/assign")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Delivery> assignDeliveryPersonnel(@RequestParam Long orderId, @RequestParam Long deliveryPersonnelId) {
        Delivery delivery = deliveryService.assignDeliveryPersonnel(orderId, deliveryPersonnelId);
        return new ResponseEntity<>(delivery, HttpStatus.CREATED);
    }


//    URL parameters: PUT /deliveries/{deliveryId}/status?status=DELIVERED
    @PutMapping("/{deliveryId}/status")
//    @PreAuthorize("hasRole('DELIVERY_PERSONNEL')")
    public ResponseEntity<Delivery> updateDeliveryStatus(@PathVariable Long deliveryId, @RequestParam OrderStatus status) {
        Delivery updatedDelivery = deliveryService.updateDeliveryStatus(deliveryId, status);
        return new ResponseEntity<>(updatedDelivery, HttpStatus.OK);
    }
}
