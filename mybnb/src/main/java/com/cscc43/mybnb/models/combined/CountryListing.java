package com.cscc43.mybnb.models.combined;

public class CountryListing {
  private String country;
  private int listing_count;

  public CountryListing() {
  }

  public String getCountry() {
    return this.country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public int getListingCount() {
    return this.listing_count;
  }

  public void setListingCount(int listing_count) {
    this.listing_count = listing_count;
  }
}
