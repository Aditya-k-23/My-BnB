package com.cscc43.mybnb.controller.requestbodies;

public class AddUserReviewBody {
  private int rating;
  private String comment;
  private int bookingId;
  private int reviewerId;
  private int reviewedId;

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

  public int getReviewerId() {
    return this.reviewerId;
  }

  public void setReviewerId(int reviewerId) {
    this.reviewerId = reviewerId;
  }

  public int getReviewedId() {
    return this.reviewedId;
  }

  public void setReviewedId(int reviewedId) {
    this.reviewedId = reviewedId;
  }

  @Override
  public String toString() {
    return "addUserReviewBody{" +
        ", rating=" + rating +
        ", comment='" + comment + '\'' +
        ", bookingId=" + bookingId +
        ", reviewerId=" + reviewerId +
        ", reviewedId=" + reviewedId +
        '}';
  }
}
