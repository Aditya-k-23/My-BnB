package com.cscc43.mybnb.models.combined;

public class CountryHostIDListing {
  private String country;
  private int host_id;
  private int listing_count;

  public CountryHostIDListing() {
  }

  public String getCountry() {
    return this.country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public int getHostId() {
    return this.host_id;
  }

  public void setHostId(int host_id) {
    this.host_id = host_id;
  }

  public int getListingCount() {
    return this.listing_count;
  }

  public void setListingCount(int listing_count) {
    this.listing_count = listing_count;
  }
}
