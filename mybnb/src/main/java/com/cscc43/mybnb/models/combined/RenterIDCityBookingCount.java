package com.cscc43.mybnb.models.combined;

public class RenterIDCityBookingCount {
  private String city;
  private int renterId;
  private int bookingCount;

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
    return this.bookingCount;
  }

  public void setBookingCount(int bookingCount) {
    this.bookingCount = bookingCount;
  }
}
