package com.cscc43.mybnb.controller.requestbodies;

public class CancelBookingBody {
  private int userId;
  private int bookingId;

  public int getUserId() {
    return this.userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getBookingId() {
    return this.bookingId;
  }

  public void setBookingId(int bookingId) {
    this.bookingId = bookingId;
  }

  @Override
  public String toString() {
    return "Cancel Booking Request Body{" +
        ", userId='" + this.userId + '\'' +
        ", bookingId='" + this.bookingId + '\'' +
        '}';
  }

}
