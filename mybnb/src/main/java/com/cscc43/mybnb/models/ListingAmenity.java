package com.cscc43.mybnb.models;

public class ListingAmenity {
  public enum Field {
    LISTING_ID("listing_id"),
    AMENITY("amenity_name");

    private final String value;

    Field(final String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  private int listing_id;
  private String amenity_name;

  public int getListingId() {
    return this.listing_id;
  }

  public void setListingId(int listing_id) {
    this.listing_id = listing_id;
  }

  public String getAmenityName() {
    return this.amenity_name;
  }

  public void setAmenityName(String amenity_name) {
    this.amenity_name = amenity_name;
  }

  @Override
  public String toString() {
    return "ListingAmenuity{"
        + "listing_id=" + this.listing_id
        + ", amenity_name='" + this.amenity_name + "'"
        + "}";
  }
}
