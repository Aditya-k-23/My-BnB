package com.cscc43.mybnb.models;

public class ListingAmenity {
  public enum Field {
    listingId("listingId"),
    AMENITY("amenityName");

    private final String value;

    Field(final String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  private int listingId;
  private String amenityName;

  public int getListingId() {
    return this.listingId;
  }

  public void setListingId(int listingId) {
    this.listingId = listingId;
  }

  public String getAmenityName() {
    return this.amenityName;
  }

  public void setAmenityName(String amenityName) {
    this.amenityName = amenityName;
  }

  @Override
  public String toString() {
    return "ListingAmenuity{"
        + "listingId=" + this.listingId
        + ", amenityName='" + this.amenityName + "'"
        + "}";
  }
}
