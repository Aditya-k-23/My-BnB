package com.cscc43.mybnb.models;

public class UserReview {
  public enum Field {
    ID("id"),
    RATING("rating"),
    COMMENT("comment"),
    BOOKING_ID("booking_id"),
    REVIEWER_ID("reviewer_id"),
    REVIEWED_ID("reviewed_id");

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
  private int booking_id;
  private int reviewer_id;
  private int reviewed_id;

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
    return this.booking_id;
  }

  public void setBookingId(int booking_id) {
    this.booking_id = booking_id;
  }

  public int getReviewerId() {
    return this.reviewer_id;
  }

  public void setReviewerId(int reviewer_id) {
    this.reviewer_id = reviewer_id;
  }

  public int getReviewedId() {
    return this.reviewed_id;
  }

  public void setReviewedId(int reviewed_id) {
    this.reviewed_id = reviewed_id;
  }

  @Override
  public String toString() {
    return "UserReview{" +
        "id=" + id +
        ", rating=" + rating +
        ", comment='" + comment + '\'' +
        ", booking_id=" + booking_id +
        ", reviewer_id=" + reviewer_id +
        ", reviewed_id=" + reviewed_id +
        '}';
  }
}
