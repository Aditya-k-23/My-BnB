package com.cscc43.mybnb.models;

import java.time.LocalDate;

public class Booking {
  public enum Field {
    ID("id"),
    STATUS("status"),
    startDate("startDate"),
    endDate("endDate"),
    listingId("listingId"),
    renterId("renterId"),
    PRICE("price");

    private final String value;

    Field(final String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  private int id;
  private String status;
  private LocalDate startDate;
  private LocalDate endDate;
  private int listingId;
  private int renterId;
  private double price;

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

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

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "Booking{" +
        "id=" + this.id +
        ", status='" + this.status + '\'' +
        ", startDate='" + this.startDate + '\'' +
        ", endDate='" + this.endDate + '\'' +
        ", listingId='" + this.listingId + '\'' +
        ", renterId='" + this.renterId + '\'' +
        ", price='" + this.price + '\'' +
        '}';
  }

}
