package com.cscc43.mybnb.models.combined;

public class RenterIDBookingCount {
  private int renterId;
  private int booking_count;

  public RenterIDBookingCount() {
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
