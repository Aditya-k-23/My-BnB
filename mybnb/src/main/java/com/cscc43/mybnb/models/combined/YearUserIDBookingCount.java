package com.cscc43.mybnb.models.combined;

public class YearUserIDBookingCount {
  private int year;
  private int userId;
  private int cancelled_count;

  public YearUserIDBookingCount() {
  }

  public int getYear() {
    return this.year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getUserId() {
    return this.userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getCancelledCount() {
    return this.cancelled_count;
  }

  public void setCancelledCount(int cancelled_count) {
    this.cancelled_count = cancelled_count;
  }
}
