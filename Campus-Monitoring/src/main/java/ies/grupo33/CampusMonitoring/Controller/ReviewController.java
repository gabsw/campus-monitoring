package ies.grupo33.CampusMonitoring.Controller;

import ies.grupo33.CampusMonitoring.Exception.ReviewNotFoundException;
import ies.grupo33.CampusMonitoring.Exception.UserCannotReviewException;
import ies.grupo33.CampusMonitoring.Model.Review;
import ies.grupo33.CampusMonitoring.Repository.ReviewRepository;
import ies.grupo33.CampusMonitoring.Services.ReviewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("review")
public class ReviewController {

    @Autowired
    private ReviewServices reviewServices;

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/local-name/{localName}")
    public Page<Review> getReviewByLocal(@PathVariable String localName,
                                  Pageable pageable) {
        return reviewServices.getReviewByLocal(localName, pageable);
    }

    @GetMapping("/username/{username}")
    public Page<Review> getReviewByUser(@PathVariable String username,
                                         Pageable pageable) {
        return reviewServices.getReviewByUser(username, pageable);
    }


    @PostMapping("/local-name/{localName}")
    public void addReview(@Valid @RequestBody Review review) throws UserCannotReviewException {
        reviewServices.addReview(review);
    }


    @PutMapping("/local-name/{localName}/{id}")
    public void updateReview(@PathVariable(value = "id") Long reviewId, @Valid @RequestBody Review review)
            throws UserCannotReviewException, ReviewNotFoundException{
        reviewServices.updateReview(reviewId, review);
    }


    @DeleteMapping("/local-name/{localName}/{id}")
    public void deleteReview(@PathVariable(value = "id") Long reviewId)
            throws UserCannotReviewException, ReviewNotFoundException {

        reviewServices.deleteReview(reviewId);
    }
}
