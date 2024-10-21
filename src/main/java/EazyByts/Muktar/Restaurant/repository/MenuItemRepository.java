package EazyByts.Muktar.Restaurant.repository;

import EazyByts.Muktar.Restaurant.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {

    List<MenuItem> findByAvailableTrue();
}
