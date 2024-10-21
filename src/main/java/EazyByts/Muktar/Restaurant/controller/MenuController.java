package EazyByts.Muktar.Restaurant.controller;

import EazyByts.Muktar.Restaurant.model.MenuItem;
import EazyByts.Muktar.Restaurant.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")//  GET  /menu
public class MenuController {

    @Autowired
    private MenuService menuService;

    // Get all available menu items (for customers)
    @GetMapping
    public List<MenuItem> getAvailableMenuItems() {
        return menuService.getAllAvailableMenuItems();
    }

    // Create a new menu item (Admin only)
    //  POST  /menu
    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public MenuItem createMenuItem(@RequestBody MenuItem menuItem) {
        return menuService.createMenuItem(menuItem);
    }

    // Update an existing menu item (Admin only)
    //   PUT     /menu/{id}
    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public MenuItem updateMenuItem(@PathVariable Long id, @RequestBody MenuItem menuItem) {
        return menuService.updateMenuItem(id, menuItem);
    }

    // Delete a menu item (Admin only)
    //    DELETE  /menu/{id}
    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        menuService.deleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }

}
