package com.cscc43.mybnb.models.combined;

public class CountryCityPostalListing {
  private String country;
  private String city;
  private String postalCode;
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
    return this.postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public int getListingCount() {
    return this.listing_count;
  }

  public void setListingCount(int listing_count) {
    this.listing_count = listing_count;
  }
}
