package ies.grupo33.CampusMonitoring.Controller;

import ies.grupo33.CampusMonitoring.Exception.ReviewNotFoundException;
import ies.grupo33.CampusMonitoring.Exception.UserCannotReviewException;
import ies.grupo33.CampusMonitoring.Model.Review;
import ies.grupo33.CampusMonitoring.Services.ReviewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("review")
public class ReviewController {
    @Autowired
    private ReviewServices reviewServices;

    @GetMapping("/username/{username}")
    public Page<Review> getReviewByUser(@PathVariable String username,
                                        Pageable pageable) {
        return reviewServices.getReviewByUser(username, pageable);
    }

    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable Long id) throws ReviewNotFoundException {
        return reviewServices.getReviewById(id);
    }

    // TODO: needs sessions information
    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable(value = "id") Long reviewId)
            throws UserCannotReviewException, ReviewNotFoundException {
        reviewServices.deleteReview(reviewId);
    }

    @PutMapping("/{id}")
    public void updateReview(@PathVariable(value = "id") Long reviewId, @Valid @RequestBody Review review)
            throws UserCannotReviewException, ReviewNotFoundException {
        reviewServices.updateReview(reviewId, review);
    }
}
