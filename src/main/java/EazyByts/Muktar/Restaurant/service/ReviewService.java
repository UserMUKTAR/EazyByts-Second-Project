package EazyByts.Muktar.Restaurant.service;


import EazyByts.Muktar.Restaurant.model.MenuItem;
import EazyByts.Muktar.Restaurant.model.Review;
import EazyByts.Muktar.Restaurant.model.Users;
import EazyByts.Muktar.Restaurant.repository.MenuItemRepository;
import EazyByts.Muktar.Restaurant.repository.ReviewRepository;
import EazyByts.Muktar.Restaurant.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {


    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Transactional
    public Review submitReview(Long customerId, Long menuItemId, int rating, String comment) {
        Users customer = userRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        Review review = new Review();
        review.setCustomer(customer);
        review.setMenuItem(menuItem);
        review.setRating(rating);
        review.setComment(comment);
        review.setReviewDate(LocalDateTime.now());

        return reviewRepository.save(review);
    }

    public List<Review> getReviewsForMenuItem(Long menuItemId) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));
        return reviewRepository.findByMenuItem(menuItem);
    }

}
