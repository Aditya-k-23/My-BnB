package com.cscc43.mybnb.models.combined;

public class CountryHostIDListing {
  private String country;
  private int hostId;
  private int count;

  public CountryHostIDListing() {
  }

  public String getCountry() {
    return this.country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public int getHostId() {
    return this.hostId;
  }

  public void setHostId(int hostId) {
    this.hostId = hostId;
  }

  public int getCount() {
    return this.count;
  }

  public void setCount(int count) {
    this.count = count;
  }
}
