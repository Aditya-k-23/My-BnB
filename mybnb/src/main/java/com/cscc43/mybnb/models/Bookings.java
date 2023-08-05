package com.cscc43.mybnb.models;

import java.time.LocalDate;

public class Bookings {
  public enum Field {
    ID("id"),
    STATUS("status"),
    START_DATE("start_date"),
    END_DATE("end_date"),
    LISTING_ID("listing_id"),
    RENTER_ID("renter_id"),
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
  private LocalDate start_date;
  private LocalDate end_date;
  private int listing_id;
  private int renter_id;
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
    return this.start_date;
  }

  public void setStartDate(LocalDate start_date) {
    this.start_date = start_date;
  }

  public LocalDate getEndDate() {
    return this.end_date;
  }

  public void setEndDate(LocalDate end_date) {
    this.end_date = end_date;
  }

  public int getListingId() {
    return this.listing_id;
  }

  public void setListingId(int listing_id) {
    this.listing_id = listing_id;
  }

  public int getRenterId() {
    return this.renter_id;
  }

  public void setRenterId(int renter_id) {
    this.renter_id = renter_id;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "Bookings{" +
        "id=" + this.id +
        ", status='" + this.status + '\'' +
        ", start_date='" + this.start_date + '\'' +
        ", end_date='" + this.end_date + '\'' +
        ", listing_id='" + this.listing_id + '\'' +
        ", renter_id='" + this.renter_id + '\'' +
        ", price='" + this.price + '\'' +
        '}';
  }

}
