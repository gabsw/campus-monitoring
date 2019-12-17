package ies.grupo33.CampusMonitoring.Services;

import ies.grupo33.CampusMonitoring.Exception.*;
import ies.grupo33.CampusMonitoring.Model.Review;
import ies.grupo33.CampusMonitoring.Model.User;
import ies.grupo33.CampusMonitoring.Repository.ReviewRepository;
import ies.grupo33.CampusMonitoring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class ReviewServices {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServices userServices;

    public Page<Review> getReviewByLocal(String localName, Pageable pageable, String username)
            throws ForbiddenUserException, LocalNotFoundException, UserNotFoundException, LoginRequiredException {
        if (localName == null) {
            throw new IllegalArgumentException("Local name is not defined.");
        } else {
            userServices.checkIfUserIsAtLocal(username, localName);
            return reviewRepository.findByLocalName(localName, pageable);
        }
    }

    public Page<Review> getReviewByUser(String username, Pageable pageable)
            throws  UserNotFoundException, LoginRequiredException {
        if (username == null) {
            throw new IllegalArgumentException("Username is not defined.");
        }

        return reviewRepository.findByUsername(username, pageable);

    }

    public Review addReviewToLocal(String localName, Review review, String username)
            throws ForbiddenUserException, LocalNotFoundException, UserNotFoundException, LoginRequiredException,
            MismatchedReviewException {
        if (review.getId() != null) {
            throw new IllegalArgumentException("Cannot add a review that already has an id.");
        }

        if (!localName.equals(review.getLocalName())) {
            throw new MismatchedReviewException("The review is not for the specified local.");
        }

        User currentUser = userServices.findUserByUsername(username);

        if (!currentUser.getUsername().equals(review.getUsername())) {
            throw new MismatchedReviewException("The reviewer does not match current user.");
        }

        userServices.checkIfUserIsAtLocal(currentUser.getUsername(), localName);

        userServices.checkIfUserIsRegular(currentUser);

        return reviewRepository.save(review);
    }


    public Review updateReview(Long reviewId, Review newReview, String username)
            throws ReviewNotFoundException, UserCannotReviewException, ForbiddenUserException, LocalNotFoundException,
            UserNotFoundException, LoginRequiredException {
        Optional<Review> oldReviewOpt = reviewRepository.findById(reviewId);

        if (!oldReviewOpt.isPresent()) {
            throw new ReviewNotFoundException("Review not found for given id " + reviewId);
        }

        Review oldReview = oldReviewOpt.get();

        if (!oldReview.getUsername().equals(newReview.getUsername())) {
            throw new UserCannotReviewException("User is not the original reviewer.");
        }

        User currentUser = userServices.findUserByUsername(username);

        userServices.checkIfUserIsAtLocal(currentUser.getUsername(), newReview.getLocalName());

        userServices.checkIfUserIsRegular(currentUser);

        oldReview.setContent(newReview.getContent());
        oldReview.setDateTime(newReview.getDateTime());
        oldReview.setRating(newReview.getRating());

        return reviewRepository.save(oldReview);
    }

    public void deleteReview(Long reviewId, String username)
    throws  UserNotFoundException, LoginRequiredException, ReviewNotFoundException,
            ForbiddenUserException, LocalNotFoundException {
        Optional<Review> review = reviewRepository.findById(reviewId);

        if (!review.isPresent()) {
            throw new ReviewNotFoundException("Review not found for given id " + reviewId);
        }

        Review reviewToDelete = review.get();

        User currentUser = userServices.findUserByUsername(username);

        if (!currentUser.getUsername().equals(reviewToDelete.getUsername())) {
            throw new ForbiddenUserException("The reviewer does not match current user.");
        }

        userServices.checkIfUserIsAtLocal(currentUser.getUsername(), reviewToDelete.getLocalName());

        userServices.checkIfUserIsRegular(currentUser);

        reviewRepository.delete(reviewToDelete);
    }

    public Review getReviewById(Long id, String username)
            throws UserNotFoundException, LoginRequiredException, ReviewNotFoundException {

        Optional<Review> review = reviewRepository.findById(id);
        if (!review.isPresent()) {
            throw new ReviewNotFoundException("Review not found for given id " + id);
        }

        User currentUser = userServices.findUserByUsername(username);

        return review.get();
    }
}
