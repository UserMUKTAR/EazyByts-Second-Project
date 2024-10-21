package EazyByts.Muktar.Restaurant.controller;


import EazyByts.Muktar.Restaurant.dto.ReviewRequestDto;
import EazyByts.Muktar.Restaurant.model.Review;
import EazyByts.Muktar.Restaurant.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Repository

@RequestMapping("/reviews")

public class ReviewController {
    @Autowired
    private ReviewService reviewService;

//    POST /reviews/submit
    @PostMapping("/submit")
//    @PreAuthorize("hasRole('CUSTOMER')")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Review> submitReview(@RequestBody ReviewRequestDto reviewRequest) {
        Review review = reviewService.submitReview(
                reviewRequest.getCustomerId(),
                reviewRequest.getMenuItemId(),
                reviewRequest.getRating(),
                reviewRequest.getComment()
        );
        return new ResponseEntity<>(review, HttpStatus.CREATED);

    }

//    GET /reviews/menu-item/{menuItemId}
    @GetMapping("/menu-item/{menuItemId}")
    public ResponseEntity<List<Review>> getReviewsForMenuItem(@PathVariable Long menuItemId) {
        List<Review> reviews = reviewService.getReviewsForMenuItem(menuItemId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }



}
