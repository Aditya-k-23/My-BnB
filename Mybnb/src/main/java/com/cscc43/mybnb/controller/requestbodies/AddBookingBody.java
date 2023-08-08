package com.cscc43.mybnb.controller.requestbodies;

import java.time.LocalDate;

public class AddBookingBody {
  private int renterId;
  private int listingId;
  private LocalDate startDate;
  private LocalDate endDate;

  public LocalDate getStartDate() {
    return this.startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return this.endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public int getListingId() {
    return this.listingId;
  }

  public void setListingId(int listingId) {
    this.listingId = listingId;
  }

  public int getRenterId() {
    return this.renterId;
  }

  public void setRenterId(int renterId) {
    this.renterId = renterId;
  }

  @Override
  public String toString() {
    return "Booking Request Body{" +
        ", startDate='" + this.startDate + '\'' +
        ", endDate='" + this.endDate + '\'' +
        ", listingId='" + this.listingId + '\'' +
        ", renterId='" + this.renterId + '\'' +
        '}';
  }
}
