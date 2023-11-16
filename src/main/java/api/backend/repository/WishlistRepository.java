package api.backend.repository;

import api.backend.entities.User;
import api.backend.entities.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist,Long> {
    List<Wishlist> findAllByUserOrderByCreatedAtDesc(User user);
}
