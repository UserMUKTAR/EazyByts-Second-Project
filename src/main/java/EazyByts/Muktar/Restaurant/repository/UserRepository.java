package EazyByts.Muktar.Restaurant.repository;



import EazyByts.Muktar.Restaurant.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    Users findByUsername(String username);
}
