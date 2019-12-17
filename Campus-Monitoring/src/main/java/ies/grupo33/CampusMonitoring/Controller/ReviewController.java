package ies.grupo33.CampusMonitoring.Controller;

import ies.grupo33.CampusMonitoring.Exception.*;
import ies.grupo33.CampusMonitoring.Model.Review;
import ies.grupo33.CampusMonitoring.Services.ReviewServices;
import ies.grupo33.CampusMonitoring.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("review")
public class ReviewController {
    @Autowired
    private ReviewServices reviewServices;

    @GetMapping("/username/{username}")
    public Page<Review> getReviewByUser(@PathVariable String username,
                                        Pageable pageable, HttpServletRequest request)
            throws ForbiddenUserException, UserNotFoundException, LoginRequiredException {
        SecurityUtils.getUserIdentity(request.getSession());
        return reviewServices.getReviewByUser(username, pageable);
    }

    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable Long id, HttpServletRequest request)
            throws UserNotFoundException, LoginRequiredException, ReviewNotFoundException {
        String username = SecurityUtils.getUserIdentity(request.getSession());
        return reviewServices.getReviewById(id, username);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable(value = "id") Long reviewId, HttpServletRequest request)
            throws UserNotFoundException, LoginRequiredException, ReviewNotFoundException,
            ForbiddenUserException, LocalNotFoundException {
        String username = SecurityUtils.getUserIdentity(request.getSession());
        reviewServices.deleteReview(reviewId, username);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable(value = "id") Long reviewId, @Valid @RequestBody Review review, HttpServletRequest request)
            throws ReviewNotFoundException, UserCannotReviewException, ForbiddenUserException, LocalNotFoundException,
            UserNotFoundException, LoginRequiredException {
        String username = SecurityUtils.getUserIdentity(request.getSession());
        Review persistedReview = reviewServices.updateReview(reviewId, review, username);
        return ResponseEntity.ok(persistedReview);
    }
}
