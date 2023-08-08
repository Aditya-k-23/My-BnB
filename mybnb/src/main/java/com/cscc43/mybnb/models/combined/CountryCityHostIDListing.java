package com.cscc43.mybnb.models.combined;

public class CountryCityHostIDListing {
  private String country;
  private String city;
  private int hostId;
  private int listing_count;

  public CountryCityHostIDListing() {
  }

  public String getCountry() {
    return this.country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public int getHostId() {
    return this.hostId;
  }

  public void setHostId(int hostId) {
    this.hostId = hostId;
  }

  public int getListingCount() {
    return this.listing_count;
  }

  public void setListingCount(int listing_count) {
    this.listing_count = listing_count;
  }
}
