package com.cscc43.mybnb.models.combined;

public class RenterIDBookingCount {
  private int renter_id;
  private int booking_count;

  public RenterIDBookingCount() {
  }

  public int getRenterId() {
    return this.renter_id;
  }

  public void setRenterId(int renter_id) {
    this.renter_id = renter_id;
  }

  public int getBookingCount() {
    return this.booking_count;
  }

  public void setBookingCount(int booking_count) {
    this.booking_count = booking_count;
  }
}
