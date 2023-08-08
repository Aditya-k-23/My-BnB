package com.cscc43.mybnb.models;

public class UserReview {
  public enum Field {
    ID("id"),
    RATING("rating"),
    COMMENT("comment"),
    bookingId("bookingId"),
    reviewerId("reviewerId"),
    reviewedId("reviewedId");

    private final String value;

    Field(final String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  private int id;
  private int rating;
  private String comment;
  private int bookingId;
  private int reviewerId;
  private int reviewedId;

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

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
    return "UserReview{" +
        "id=" + id +
        ", rating=" + rating +
        ", comment='" + comment + '\'' +
        ", bookingId=" + bookingId +
        ", reviewerId=" + reviewerId +
        ", reviewedId=" + reviewedId +
        '}';
  }
}
