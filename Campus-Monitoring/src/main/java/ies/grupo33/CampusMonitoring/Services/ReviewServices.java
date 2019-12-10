package ies.grupo33.CampusMonitoring.Services;

import ies.grupo33.CampusMonitoring.Exception.ReviewNotFoundException;
import ies.grupo33.CampusMonitoring.Exception.UserCannotReviewException;
import ies.grupo33.CampusMonitoring.Model.Review;
import ies.grupo33.CampusMonitoring.Repository.ReviewRepository;
import ies.grupo33.CampusMonitoring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ReviewServices {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    public Page<Review> getReviewByLocal(String localName, Pageable pageable) {
        if (localName == null) {
            throw new IllegalArgumentException("Local name is not defined.");
        } else {
            return reviewRepository.findByLocalName(localName, pageable);
        }
    }

    public Page<Review> getReviewByUser(String username, Pageable pageable) {
        if (username == null) {
            throw new IllegalArgumentException("Username is not defined.");
        } else {
            return reviewRepository.findByUsername(username, pageable);
        }
    }

    public void addReview(Review review) throws UserCannotReviewException {

        if (checkIfUserIsAtLocal(review.getLocalName(), review.getUsername())) {
            reviewRepository.save(review);
        } else {
            throw new UserCannotReviewException("User does not have permission to review local.");
        }

    }

    private boolean checkIfUserIsAtLocal(String localName, String username) {

        if (username == null) {
            throw new IllegalArgumentException("Username is not defined.");
        } else {
            return userRepository.findByLocalsNameAndUsername(localName, username).isPresent();
        }

    }

    public void updateReview(Long reviewId, Review newReview) throws ReviewNotFoundException, UserCannotReviewException {

        Optional<Review> oldReviewOpt = reviewRepository.findById(reviewId);

        if (!oldReviewOpt.isPresent()) {
            throw new ReviewNotFoundException("Review not found for given id " + reviewId);
        }

        if (!checkIfUserIsAtLocal(newReview.getLocalName(), newReview.getUsername())) {
            throw new UserCannotReviewException("User does not have permission to review local.");
        }

        Review oldReview = oldReviewOpt.get();

        if (!oldReview.getUsername().equals(newReview.getUsername())) {
            throw new UserCannotReviewException("User is not the original reviewer.");
        }

        oldReview.setContent(newReview.getContent());
        oldReview.setDateTime(newReview.getDateTime());
        oldReview.setRating(newReview.getRating());

        reviewRepository.save(oldReview);
    }

    public void deleteReview(Long reviewId) throws ReviewNotFoundException, UserCannotReviewException {

        Optional<Review> review = reviewRepository.findById(reviewId);

        if (!review.isPresent()) {
            throw new ReviewNotFoundException("Review not found for given id " + reviewId);
        }

        Review reviewToDelete = review.get();

        if (!checkIfUserIsAtLocal(reviewToDelete.getLocalName(), reviewToDelete.getUsername())) {
            throw new UserCannotReviewException("User does not have permission to review local.");
        }

        reviewRepository.delete(reviewToDelete);
    }

}
