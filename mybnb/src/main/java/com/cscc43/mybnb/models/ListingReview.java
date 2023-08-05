package com.cscc43.mybnb.models;

public class ListingReview {
  public enum Field {
    ID("id"),
    RATING("rating"),
    COMMENT("comment"),
    BOOKING_ID("booking_id"),
    RENTER_ID("renter_id"),
    LISTING_ID("listing_id");

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
  private int renter_id;
  private int listing_id;

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

  public int getRenterId() {
    return this.renter_id;
  }

  public void setRenterId(int renter_id) {
    this.renter_id = renter_id;
  }

  public int getListingId() {
    return this.listing_id;
  }

  public void setListingId(int listing_id) {
    this.listing_id = listing_id;
  }

  @Override
  public String toString() {
    return "ListingReview {"
        + "id=" + id + ", "
        + "rating=" + rating + ", "
        + "comment=" + comment + ", "
        + "booking_id=" + booking_id + ", "
        + "renter_id=" + renter_id + ", "
        + "listing_id=" + listing_id + ", "
        + "}";
  }
}
