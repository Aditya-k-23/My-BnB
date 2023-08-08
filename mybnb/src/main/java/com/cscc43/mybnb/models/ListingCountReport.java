package com.cscc43.mybnb.models;

public class ListingCountReport {
  private int host_city_count;
  private String country;
  private String city;
  private int hostId;
  private int city_listing_count;

  public int getHostCityCount() {
    return this.host_city_count;
  }

  public void setHostCityCount(int host_city_count) {
    this.host_city_count = host_city_count;
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

  public int getCityListingCount() {
    return this.city_listing_count;
  }

  public void setCityListingCount(int city_listing_count) {
    this.city_listing_count = city_listing_count;
  }

  @Override
  public String toString() {
    return "{" +
        " host_city_count='" + host_city_count + "'" +
        ", country='" + country + "'" +
        ", city='" + city + "'" +
        ", hostId='" + hostId + "'" +
        ", city_listing_count='" + city_listing_count + "'" +
        "}";
  }
}
