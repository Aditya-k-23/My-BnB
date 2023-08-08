package com.cscc43.mybnb.models.combined;

public class RenterIDBookingCount {
  private int renterId;
  private int bookingCount;

  public RenterIDBookingCount() {
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
