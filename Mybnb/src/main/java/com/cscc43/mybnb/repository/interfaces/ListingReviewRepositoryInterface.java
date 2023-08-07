package com.cscc43.mybnb.repository.interfaces;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.cscc43.mybnb.models.ListingReview;

@Repository
public interface ListingReviewRepositoryInterface {
  public List<ListingReview> getAllListingReviews();

  public ListingReview getListingReviewByReviewId(int id);

  public List<ListingReview> getListingReviewsByListing(int listing_id);

  public List<ListingReview> getListingReviewsByReviewerId(int reviewer_id);

  public List<ListingReview> getListingReviewByBookingId(int booking_id);

  public void addListingReview(ListingReview listingReview);

  public void deleteListingReview(int reviewId);

}
