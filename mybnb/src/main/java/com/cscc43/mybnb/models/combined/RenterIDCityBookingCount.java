package com.cscc43.mybnb.models.combined;

public class RenterIDCityBookingCount {
  private String city;
  private int renterId;
  private int booking_count;

  public RenterIDCityBookingCount() {
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public int getRenterId() {
    return this.renterId;
  }

  public void setRenterId(int renterId) {
    this.renterId = renterId;
  }

  public int getBookingCount() {
    return this.booking_count;
  }

  public void setBookingCount(int booking_count) {
    this.booking_count = booking_count;
  }
}
