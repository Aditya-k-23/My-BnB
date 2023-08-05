package com.cscc43.mybnb.models.combined;

public class CountryCityPostalListing {
  private String country;
  private String city;
  private String postal_code;
  private int listing_count;

  public CountryCityPostalListing() {
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

  public String getPostalCode() {
    return this.postal_code;
  }

  public void setPostalCode(String postal_code) {
    this.postal_code = postal_code;
  }

  public int getListingCount() {
    return this.listing_count;
  }

  public void setListingCount(int listing_count) {
    this.listing_count = listing_count;
  }
}
