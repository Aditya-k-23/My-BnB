package com.cscc43.mybnb.controller.requestbodies;

public class AddListingReviewBody {
  private int rating;
  private String comment;
  private int bookingId;
  private int renterId;
  private int listingId;

  public int getRating() {
    return this.rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public String getComment() {
    return this.comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public int getBookingId() {
    return this.bookingId;
  }

  public void setBookingId(int bookingId) {
    this.bookingId = bookingId;
  }

  public int getRenterId() {
    return this.renterId;
  }

  public void setRenterId(int renterId) {
    this.renterId = renterId;
  }

  public int getListingId() {
    return this.listingId;
  }

  public void setListingId(int listingId) {
    this.listingId = listingId;
  }

  @Override
  public String toString() {
    return "ListingReview {"
        + "rating=" + rating + ", "
        + "comment=" + comment + ", "
        + "bookingId=" + bookingId + ", "
        + "renterId=" + renterId + ", "
        + "listingId=" + listingId + ", "
        + "}";
  }

}
