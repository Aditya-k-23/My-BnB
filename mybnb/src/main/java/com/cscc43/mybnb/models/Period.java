package com.cscc43.mybnb.models;

import java.time.LocalDate;

public class Period {
  public enum Field {
    ID("id"),
    START_DATE("start_date"),
    END_DATE("end_date"),
    PRICE("price"),
    LISTING_ID("listing_id");

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
  private LocalDate start_date;
  private LocalDate end_date;
  private double price;
  private int listing_id;

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
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

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getListingId() {
    return this.listing_id;
  }

  public void setListingId(int listing_id) {
    this.listing_id = listing_id;
  }

  @Override
  public String toString() {
    return "Period{" +
        "id=" + this.id +
        ", start_date='" + this.start_date + '\'' +
        ", end_date='" + this.end_date + '\'' +
        ", price='" + this.price + '\'' +
        ", listing_id='" + this.listing_id + '\'' +
        '}';
  }
}
