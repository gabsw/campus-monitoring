package ies.grupo33.CampusMonitoring.Services;

import ies.grupo33.CampusMonitoring.Exception.MismatchedReviewException;
import ies.grupo33.CampusMonitoring.Exception.ReviewNotFoundException;
import ies.grupo33.CampusMonitoring.Exception.UserCannotReviewException;
import ies.grupo33.CampusMonitoring.Model.Review;
import ies.grupo33.CampusMonitoring.Model.User;
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

    public void addReviewToLocal(String localName, Review review) throws UserCannotReviewException, MismatchedReviewException {
        if (review.getId() != null) {
            throw new IllegalArgumentException("Cannot add a review that already has an id.");
        }

        if (!localName.equals(review.getLocalName())) {
            throw new MismatchedReviewException("The review is not for the specified local.");
        }

        if (checkIfRegularUserIsAtLocal(review.getLocalName(), review.getUsername())) {
            reviewRepository.save(review);
        } else {
            throw new UserCannotReviewException("User does not have permission to review local.");
        }

    }

    private boolean checkIfRegularUserIsAtLocal(String localName, String username) throws UserCannotReviewException {

        if (username == null) {
            throw new IllegalArgumentException("Username is not defined.");
        }

        Optional<User> user = userRepository.findByLocalsNameAndUsername(localName, username);

        if (!user.isPresent()) {
            throw new UserCannotReviewException("User does not have permission to review local.");
        }

        User reviewer = user.get();

        if (reviewer.isAdmin()) {
            throw new UserCannotReviewException("Admin does not have permission to review local.");
        }
        return true;
    }

    public void updateReview(Long reviewId, Review newReview) throws ReviewNotFoundException, UserCannotReviewException {
        Optional<Review> oldReviewOpt = reviewRepository.findById(reviewId);

        if (!oldReviewOpt.isPresent()) {
            throw new ReviewNotFoundException("Review not found for given id " + reviewId);
        }

        if (!checkIfRegularUserIsAtLocal(newReview.getLocalName(), newReview.getUsername())) {
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

        if (!checkIfRegularUserIsAtLocal(reviewToDelete.getLocalName(), reviewToDelete.getUsername())) {
            throw new UserCannotReviewException("User does not have permission to review local.");
        }

        reviewRepository.delete(reviewToDelete);
    }

    public Review getReviewById(Long id) throws ReviewNotFoundException {

        Optional<Review> review = reviewRepository.findById(id);
        if (!review.isPresent()) {
            throw new ReviewNotFoundException("Review not found for given id " + id);
        }

        return review.get();
    }
}
