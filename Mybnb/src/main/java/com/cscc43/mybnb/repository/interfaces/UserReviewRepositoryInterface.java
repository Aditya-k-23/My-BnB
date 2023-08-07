package com.cscc43.mybnb.repository.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cscc43.mybnb.models.UserReview;

@Repository
public interface UserReviewRepositoryInterface {
  public List<UserReview> getAllUserReviews();

  public UserReview getUserReviewByReviewId(int id);

  public List<UserReview> getUserReviewsForUser(int reviewedId);

  public List<UserReview> getUserReviewsByUser(int reviewerId);

  public List<UserReview> getUserReviewByBookingId(int bookingId);

  public void addUserReview(UserReview userReview);

  public void deleteUserReview(int reviewId);
}
