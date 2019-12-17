package ies.grupo33.CampusMonitoring.Controller;

import ies.grupo33.CampusMonitoring.Exception.*;
import ies.grupo33.CampusMonitoring.Model.Review;
import ies.grupo33.CampusMonitoring.Services.ReviewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        return reviewServices.getReviewByUser(username, pageable, request.getSession());
    }

    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable Long id, HttpServletRequest request)
            throws UserNotFoundException, LoginRequiredException, ReviewNotFoundException {
        return reviewServices.getReviewById(id, request.getSession());
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable(value = "id") Long reviewId, HttpServletRequest request)
            throws  UserNotFoundException, LoginRequiredException, ReviewNotFoundException,
            ForbiddenUserException, LocalNotFoundException {
        reviewServices.deleteReview(reviewId, request.getSession());
    }

    @PutMapping("/{id}")
    public void updateReview(@PathVariable(value = "id") Long reviewId, @Valid @RequestBody Review review, HttpServletRequest request)
            throws ReviewNotFoundException, UserCannotReviewException, ForbiddenUserException, LocalNotFoundException,
            UserNotFoundException, LoginRequiredException {
        reviewServices.updateReview(reviewId, review, request.getSession());
    }
}
