package com.cscc43.mybnb.models;

public class ListingReview {
  public enum Field {
    ID("id"),
    RATING("rating"),
    COMMENT("comment"),
    bookingId("bookingId"),
    RENTER_ID("renter_id"),
    listingId("listingId");

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
  private int renter_id;
  private int listingId;

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

  public int getRenterId() {
    return this.renter_id;
  }

  public void setRenterId(int renter_id) {
    this.renter_id = renter_id;
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
        + "id=" + id + ", "
        + "rating=" + rating + ", "
        + "comment=" + comment + ", "
        + "bookingId=" + bookingId + ", "
        + "renter_id=" + renter_id + ", "
        + "listingId=" + listingId + ", "
        + "}";
  }
}
