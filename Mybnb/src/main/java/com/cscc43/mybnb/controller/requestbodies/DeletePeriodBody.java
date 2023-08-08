package com.cscc43.mybnb.controller.requestbodies;

public class DeletePeriodBody {
  private int periodId;
  private int listingId;

  public int getPeriodId() {
    return periodId;
  }

  public void setPeriodId(int periodId) {
    this.periodId = periodId;
  }

  public int getListingID() {
    return listingId;
  }

  public void setListingID(int listingId) {
    this.listingId = listingId;
  }

  @Override
  public String toString() {
    return "DeletePeriodBody{" +
        "periodId=" + periodId +
        ", listingId=" + listingId +
        '}';
  }
}
