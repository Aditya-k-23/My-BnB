package com.cscc43.mybnb.models;

import java.time.LocalDate;

public class Period {
  public enum Field {
    ID("id"),
    startDate("startDate"),
    endDate("endDate"),
    PRICE("price"),
    listingId("listingId");

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
  private LocalDate startDate;
  private LocalDate endDate;
  private double price;
  private int listingId;

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
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

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getListingId() {
    return this.listingId;
  }

  public void setListingId(int listingId) {
    this.listingId = listingId;
  }

  @Override
  public String toString() {
    return "Period{" +
        "id=" + this.id +
        ", startDate='" + this.startDate + '\'' +
        ", endDate='" + this.endDate + '\'' +
        ", price='" + this.price + '\'' +
        ", listingId='" + this.listingId + '\'' +
        '}';
  }
}
